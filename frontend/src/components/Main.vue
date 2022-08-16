<template>
  <div v-if="this.store.user">
    <MenuBar page="Main" />
      <div style="height:200px; background-color:#845ef7;padding:5px 15px 15px 15px;">
        <div style="height:70%; padding:5px;text-align:center;">
          <p style="font-size:30px;font-weight:bold;color:white"> {{addComma(this.store.user.userPoint)}} P</p>
          <p style="font-size:25px;color:white">총 5회 매칭되었어요 !</p>
        </div>
        <div style="display:flex;justify-content:center;">
          <v-btn id="main-btn" class="mr-5" @click="reInfo()">정보 재등록</v-btn> <v-btn id="main-btn" @click="rePrefernce()">성향 재파악</v-btn>
        </div>
      </div>
    <v-col>
      <v-row id="match" @click="goFaceMatching">
        <!-- <div style="width: 35%; display:flex;align-items: center; justify-content:center;"><img src="../assets/purpleheart.png" /></div> -->
        <div
          style="
            width: 100%;
            text-align: center;
            font-size: 40px;
            font-weight: bold;
          "
        >
          외모로 연결
        </div>
      </v-row>

      <v-row id="match" @click="goMindMatching">
        <div
          style="
            width: 100%;
            text-align: center;
            font-size: 40px;
            font-weight: bold;
          "
        >
          마음으로 연결
        </div>
        <!-- <div style="width: 35%; display:flex;align-items: center; justify-content:center;"><img src="../assets/purpleheart.png" /></div> -->
      </v-row>

      <v-row id="sub">
        <v-col id="chat">
          <div class="d-flex mb-2" style="justify-content: center">
            <!-- <div style="width: 25px; height: 25px"></div> -->
            <img id="img2" src="../assets/icons/chat2.png" />
            <!-- <div id="chat-count">5</div> -->
          </div>
          <div style="font-size: 25px; font-weight: bold">대화하기</div>
        </v-col>
        <v-col id="service">
          <div class="d-flex mb-2" style="justify-content: center">
            <img id="img2" src="../assets/icons/service2.png" />
          </div>
          <div style="font-size: 25px; font-weight: bold">사후서비스</div>
        </v-col>
      </v-row>
    </v-col>
    <!-- <div class="bottom_menu">
      <div>
        <v-icon large>mdi-home-variant</v-icon>
      </div>
      <div>
        <v-icon large>mdi-chat</v-icon>
      </div>
      <div>
        <v-icon large>mdi-list-box</v-icon>
      </div>
      <div>
        <v-icon large>mdi-account</v-icon>
      </div>
    </div> -->
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import MenuBar from "./MenuBar";
import { useAppStore } from '../store/userState'

export default {
  name: "Main",
  components:{
    MenuBar
  },
  setup(){
        const store = useAppStore()
        return {store}
    },
  data: () => ({}),
  methods: {
    goFaceMatching() {
      this.$router.push("/faceSelect").catch(() => {});
    },
    goMindMatching() {
      this.$router.push("/mindQuestion").catch(() => {});
    },
    addComma(price) {
            price = price + ""
            var regexp = /\B(?=(\d{3})+(?!\d))/g
            return price.toString().replace(regexp, ',')
    },
    reInfo(){
      this.$router.push("/basicInfoList").catch(() => {});
    },
    rePrefernce(){
      this.$router.push("/preferenceQuestion").catch(() => {});
    },
    testSocket() {
      // const sock = new SockJS("http://10.214.3.43:8081/match/chatWebSocket");
      // sock.onopen = () => {
      //   console.log("open");
      //   sock.send("test");
      // };
      // const socket = io("/chatSocket", {
      //   withCredentials: false,
      //   // extraHeaders: {
      //   //   "Access-Control-Allow-Origin": "*",
      //   // },
      // });

      const socket = new SockJS("http://10.214.3.43:8082/chat/chatWebSocket");

      console.log("constructed socket");

      this.stompClient = Stomp.over(socket);

      this.stompClient.connect(
        {},
        () => {
          // 소켓 연결 성공
          this.connected = true;
          // console.log('소켓 연결 성공', frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          // console.log(this.teamId)
          this.stompClient.subscribe("/chatSocket/" + this.teamId, (res) => {
            // console.log('구독으로 받은 메시지 입니다.', res.body);
            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            // console.log(JSON.parse(res.body));
            this.recvList.push(JSON.parse(res.body));
            this.scrolltoBottom();
          });
          console.log("success");
        },
        // on error
        () => {
          console.log("failed");
          // 소켓 연결 실패
          // console.log('소켓 연결 실패', error);
          this.connected = false;
        },
      );

      console.log("success");

      // socket.on("connect", () => {
      //   console.log("connecting");
      //   console.log(socket.id); // x8WIv7-mJelg7on_ALbx
      // });

      // socket.on("disconnect", () => {
      //   console.log(socket.id); // undefined
      // });
    },
  },
  created() {
    // this.testSocket();
  },
};
</script>

<style>
#match {
  border-radius: 10px;
  height:150px;
  /* border: 1px solid; */
  padding: 13px;
  margin: 20px;
  /* margin-top:30px; */
  display: flex;
  align-items: center;
  box-shadow: 0 0 15px 0 rgba(0, 0, 0, 0.2);
  background-color:white;
  /* background:linear-gradient(to right bottom, #7950f2, #b197fc); */
}
#sub {
  margin: 20px;
  display: flex;
  align-items: center;
  margin-top: 20px;
}

#chat {
  border-radius: 10px;
  margin-right: 10px;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 20px;
  box-shadow: 0 0 15px 0 rgba(0, 0, 0, 0.2);
  margin-right:30px;
  background-color:white;
}
#service {
  border-radius: 10px;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 20px;
  box-shadow: 0 0 15px 0 rgba(0, 0, 0, 0.2);
  background-color:white;
}

img {
  width: 90px;
  height: 90px;
}

#img2 {
  width: 70px;
  height: 70px;
}
#chat-count {
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid;
  border-radius: 100%;
  width: 25px;
  height: 25px;
}

.bottom_menu {
  position: fixed;
  bottom: 0px;
  left: 0px;
  width: 100%;
  height: 80px;
  z-index: 100;
  border-top: 1px solid gray;
  background-color: white;
}
.bottom_menu > div {
  float: left;
  width: 25%;
  height: 100%;
  display: flex;
  text-align: center;
  justify-content: center;
  /* padding-top: 13px;  */
}

#main-btn{
  width:47%;
  font-size:20px;
  height:50px;
  border-radius: 15px;
  background-color:#f3f0ff;
}

p{
  margin:0px !important;
}
</style>
