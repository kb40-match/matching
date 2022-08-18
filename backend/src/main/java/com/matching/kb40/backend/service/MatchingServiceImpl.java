package com.matching.kb40.backend.service;

import com.matching.kb40.backend.dao.MatchingDao;
import com.matching.kb40.backend.dto.MatchDto;
import com.matching.kb40.backend.model.MatchingResult;
import com.matching.kb40.backend.model.UserDataOfMatching;
import io.swagger.models.auth.In;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class MatchingServiceImpl implements MatchingService{

    @Autowired
	private MatchingDao matchingDao;

	@Override
	public Boolean register(MatchDto match) throws Exception {
		if(matchingDao.checkForInsert2(match) > 0){
			// 요청자(sender)가 상대(receiver)에게 중복요청을 함
			return false;
		}
		else if(matchingDao.checkForInsert(match) > 0) {
			// 상대(receiver)가 이미 대화중
			return false;
		}
		else{
			matchingDao.insertForRequest(match);
			// 대화 신청 완료
			return true;
		}
	}

	@Override
	public Boolean modifyAccept(MatchDto match) throws Exception {
		if (matchingDao.checkForUpdate(match) > 0) {
			// 상대(sender)가 이미 대화중
			return false;
		} else {
			matchingDao.updateForAccept(match);
			matchingDao.updateForOtherQuit(match);

			// 대화 수락 완료
			return true;
		}
	}

	@Override
	public Boolean modifyReject(MatchDto match) throws Exception {
		matchingDao.updateForReject(match);

		return true;
	}

	@Override
	public Boolean modifyFinish(MatchDto match) throws Exception {
		matchingDao.updateForFinish(match);

		return true;
	}

	@Override
	public List<UserDataOfMatching> findReceiver(String senderId) throws Exception {
		return matchingDao.retrieveReceiver(senderId);
	}

	@Override
	public List<UserDataOfMatching> findSender(String receiverId) throws Exception {
		return matchingDao.retrieveSender(receiverId);
	}

	@Override
	public List<MatchingResult> findResult(String userId) throws Exception {
		List<MatchingResult> resultList = new LinkedList<>();
		List<MatchingResult> result5 = new LinkedList<>();
		MatchingResult userData = matchingDao.retrieveMatchingResult(userId);

		// NOTE: you must manually set API_KEY below using information retrieved from your IBM Cloud account.

		String API_KEY = "nbaWdZ6hp9LfNnq9J83w7x5lzzSZw8uY-wrZfJ8uHsiD";

		HttpURLConnection tokenConnection = null;
		HttpURLConnection scoringConnection = null;
		BufferedReader tokenBuffer = null;
		BufferedReader scoringBuffer = null;
		try {
			// Getting IAM token
			URL tokenUrl = new URL("https://iam.cloud.ibm.com/identity/token?grant_type=urn:ibm:params:oauth:grant-type:apikey&apikey=" + API_KEY);
			tokenConnection = (HttpURLConnection) tokenUrl.openConnection();
			tokenConnection.setDoInput(true);
			tokenConnection.setDoOutput(true);
			tokenConnection.setRequestMethod("POST");
			tokenConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			tokenConnection.setRequestProperty("Accept", "application/json");
			tokenBuffer = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
			StringBuffer jsonString = new StringBuffer();
			String line;
			while ((line = tokenBuffer.readLine()) != null) {
				jsonString.append(line);
			}
			// Scoring request
			URL scoringUrl = new URL("https://us-south.ml.cloud.ibm.com/ml/v4/deployments/6af6d990-51d6-4d9a-858e-385262a1dc5a/predictions?version=2022-08-16");
			String iam_token = "Bearer " + jsonString.toString().split(":")[1].split("\"")[1];
			scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
			scoringConnection.setDoInput(true);
			scoringConnection.setDoOutput(true);
			scoringConnection.setRequestMethod("POST");
			scoringConnection.setRequestProperty("Accept", "application/json");
			scoringConnection.setRequestProperty("Authorization", iam_token);
			scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(scoringConnection.getOutputStream(), "UTF-8");

			// NOTE: manually define and pass the array(s) of values to be scored in the next line
			int param1 = 1;
			int param2 = 30;
			int param3 = 3;

			String value ="";
			value += userData.getNum() + "," ;
			value += "\"" + userData.getUserId() + "\"" + ",";
			value += "\"" + userData.getName() + "\"" + ",";
			value += "\"" + userData.getGender() + "\"" + ",";
			value += userData.getAge() + ",";
			value += "\"" + userData.getAddress() + "\"" + ",";
			value += userData.getAssets() + ",";
			value += "\"" + userData.getConsumePtn1() + "\"" + ",";
			value += "\"" + userData.getConsumePtn2() + "\"" + ",";
			value += "\"" + userData.getConsumePtn3() + "\"" + ",";
			value += "\"" + userData.getInvestProp() + "\"" + ",";
			value += "\"" + userData.getJob() + "\"" + ",";
			value += "\"" + userData.getMbtiMind() + "\"" + ",";
			value += "\"" + userData.getMbtiRecog() + "\"" + ",";
			value += "\"" + userData.getMbtiJudge() + "\"" + ",";
			value += "\"" + userData.getMbtiTactics() + "\"" + ",";
			value += "\"" + "none" + "\"" + ",";
			value += "\"" + userData.getProfileFilename() + "\"" + ",";
			value += "\"" + "none" + "\"" + ",";
			value += userData.getUserPoint() + ",";
			value += "\"" + userData.getPetPrefer() + "\"" + ",";
			value += "\"" + userData.getNickname() + "\"" + ",";
			value += "\"" + userData.getAgreeFlag() + "\"" + ",";
			value += "\"" + userData.getHobby() + "\"";

			String payload = "{\"input_data\": [{\"fields\": [\"순서\", \"ID\", \"이름\", \"성별\", \"나이\", \"거주지\", \"자산\", \"소비패턴 1순위\", \"소비패턴 2순위\", \"소비패턴 3순위\", \"투자 성향\", \"직업\", \"MBTI-마음(E, I)\", \"MBTI-인식(N, S)\", \"MBTI-판단(F, T)\", \"MBTI-전술(P, J)\", \"origin name\", \"filename\", \"path\", \"포인트\", \"반려동물 선호\", \"닉네임\", \"정보제공 동의여부\", \"취미\"],\"values\": [[" + value + "]]}]}";

			writer.write(payload);
			writer.close();


			scoringBuffer = new BufferedReader(new InputStreamReader(scoringConnection.getInputStream()));
			StringBuffer jsonStringScoring = new StringBuffer();
			String lineScoring;
			while ((lineScoring = scoringBuffer.readLine()) != null) {
				jsonStringScoring.append(lineScoring);
			}
			System.out.println("1. API call result: "+jsonStringScoring);
			String objString = jsonStringScoring +"";

			JSONObject obj = new JSONObject(objString);
			String predicArray = obj.getJSONArray("predictions").getJSONObject(0).getJSONArray("values").getJSONArray(0).get(0).toString();
			System.out.println("2. predict result: " + predicArray);

			String userIDArray = obj.getJSONArray("predictions").getJSONObject(0).getJSONArray("values").getJSONArray(0).getJSONArray(0).get(0).toString();
			System.out.println("3. userIDArray result: " + userIDArray);


			String similarityPerIDArray = obj.getJSONArray("predictions").getJSONObject(0).getJSONArray("values").getJSONArray(0).getJSONArray(0).get(1).toString();
			System.out.println("4. similarityPerIDArray result: " + similarityPerIDArray);

			// ----- 데이터 가공 -----

			Random rand = new Random();

			int[] order = new int[5];

			for(int i=0; i<order.length; i++) {
				order[i] = rand.nextInt(10);

				for(int j=0; j<i; j++) {
					if(order[i] == order[j]) i--;
				}
			}

			Arrays.sort(order);

			userIDArray = userIDArray.substring(2, userIDArray.length() - 2);
			String[] userIdList = userIDArray.split("\",\"");

			similarityPerIDArray = similarityPerIDArray.substring(2, similarityPerIDArray.length() - 2);
			String[] similarityPerList = similarityPerIDArray.split("\",\"");

			Integer[] fitPercentList = new Integer[10];


			for(int i=0; i<similarityPerList.length; i++) {
				fitPercentList[i] = (int)Math.round((2.0 - Double.parseDouble(similarityPerList[i])) * 100);
			}

			Integer base = 0;
			if(fitPercentList[9] < 0) base = fitPercentList[9] * -1;

			for(int i=0; i<10; i++){
				resultList.add(matchingDao.retrieveMatchingResult(userIdList[i]));

				if(fitPercentList[i] + base > 100 ) {
					resultList.get(i).setFitPercent(100);
				}
				else {
					resultList.get(i).setFitPercent(fitPercentList[i] + base);
				}
			}

			for(int ord : order) result5.add(resultList.get(ord));

		} catch (IOException e) {
			System.out.println("The URL is not valid.");
			System.out.println(e.getMessage());
		}
		finally {
			if (tokenConnection != null) {
				tokenConnection.disconnect();
			}
			if (tokenBuffer != null) {
				tokenBuffer.close();
			}
			if (scoringConnection != null) {
				scoringConnection.disconnect();
			}
			if (scoringBuffer != null) {
				scoringBuffer.close();
			}
		}

		return result5;
	}

	@Override
	public String findMatchId(String userId) throws Exception {
		String matchId = matchingDao.retrieveMatchId(userId);

		if(matchId == null) {
			return "false";
		}
		else {
			return matchId;
		}
	}
}
