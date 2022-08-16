import axios from "axios";
import { useAppStore } from "../store/userState";

export const fetchUser = async (userId) => {
  const response = await axios.get(`/user/${userId}`);
  return response.data;
};

export const fetchMyData = async (userId) => {
  const response = await axios.get(`/user/mydata/${userId}`);
  return response.data;
};

export const fetchResultFromAI = async (userId) => {
  const response = await axios.get(`matching/result/${userId}`); // FIXME: change path
  return response.data;
}

export const loadUser = async (userId) => {
  const store = useAppStore();
  store.user = await fetchUser(userId);
};

export const loadMydata = async (userId) => {
  const store = useAppStore();
  store.myData = await fetchMyData(userId);
};

export const loadResultFromAI = async (userId) => {
  const store = useAppStore();
  store.aiResult = await fetchResultFromAI(userId);
}

export const setUser = async () => {
  const store = useAppStore();

    await axios.put(`/user`, store.user)
    .then((response) => {
      console.log("업데이트 완료")
      console.log(response.data)
      console.log(store.user)
    })
    .catch((err)=>{
      console.log(err.response);
    });
    console.log(this.user)
};
