<template>
<v-container>
    <MenuBar page="FaceSelect" />
    <div>
        <div style="font-size:20px;text-align:center;margin-top:30px;">
            <div>대화를 요청하시려면</div>
            <div class="d-flex" style="justify-content: center;align-items: center;">아래 <div id="chat-mini"> <v-icon small style="color:white">mdi-chat</v-icon> </div> 를 눌러주세요! </div>
        </div>
    <div id="user">
        <div id="user-img"><img :src=getProfile(user.profileFilename) style="height:260px;width:260px;border-radius:100%;" /></div>
        <div id="user-info">
            <div style="display:flex; align-items:center;">
                <div style="width:220px;">
                <div style="font-size:21px;">{{user.nickname}}, {{mydata.age}}세</div>
                <div style="font-size:15px;"><v-icon small style="color:black;">mdi-map-marker</v-icon>{{mydata.address}}</div>
                </div>
                <div id="chat" @click="goChat">
                    <v-icon style="color:white">mdi-chat</v-icon>
                </div>
            </div>
        </div>
    </div>
    <div style="text-align:center; margin-top:20px;">
        <v-btn text rounded large dark @click="displayDetail" id="btn" >상세정보 보기</v-btn>
        <v-btn rounded text large dark @click="reMatching" id="btn">다시하기 (-500P)</v-btn>
    </div>
</div>
</v-container>

</template>

<script>
import dayjs from "dayjs";
import { useAppStore } from '../../store/userState'
import MenuBar from "../MenuBar.vue";

export default {
    components:{
        MenuBar
    },
    setup(){
        const store = useAppStore()
        return {store}
    },
    data(){
        return{
            mydata:{},
            match:{
                sender:'',
                receiver:'',
                activeFlag:'',
                createdData:''
            }
        }
    },
    props:{
        user:{
            type:Object
        }
    },
    methods: {
        getMydata(){
            this.$axios
                .get(`/user/mydata/${this.user.userId}`)
                .then((response) => {
                    this.mydata =  response.data
                })
                .catch((err) => {
                    console.log(err.response)
                });
        },
        displayDetail() {
            this.$router.push({name: 'MatchDetail', params: {user: this.user, mydata : this.mydata}})
        },
        reMatching() {
            this.store.user.point -= 500 // decrease 500 point
            this.$router.push("/faceSelect").catch(() => {});
        },
        goChat(){
            this.match.sender=this.store.user.userId
            this.match.receiver=this.user.userId
            this.match.activeFlag = '0'
            this.match.createdDate = dayjs().format("YYYYMMDDHHmmss")

            this.$axios.post(`/matching/request`, this.match)
            .then(()=>{
                console.log("요청하였습니다.")
            }).catch((err)=>{
                console.log(err.response)
            })
        },
        getProfile(i){
            return require("@/assets/" + i)
        }
    },
    mounted() { 
        this.getMydata()
    }
}
</script>


<style scoped>
#btn{
    font-size:18px;
    /* margin-right: 15px; */
    margin-top: 8px;
    background: #8452f7;
    width:88%;
    height:50px;
}

#user{
    border : 2px solid #8452f7;
    border-radius: 30px;
    margin : 20px;
    height : 450px;
}
#user-img{
    height:310px; 
    display:flex;
    justify-content: center;
    align-items: center;
}
#user-info{
    /* border : 1px solid; */
    border-radius: 30px;
    height:100px;
    margin:20px 10px 20px 10px;
    padding:15px;
    align-items: center;
    display:flex;
    background-color:#f3f0ff;
}
#chat{
    width:50px; 
    height:50px; 
    display:flex; 
    align-items:center; 
    justify-content:center; 
    border-radius:100%;
    background-color:#8452f7;
    border:none;
}

#chat-mini{
    width:30px; 
    height:30px; 
    display:flex; 
    align-items:center; 
    justify-content:center; 
    border-radius:100%;
    background-color:#8452f7;
    border:none;
    margin-right:2px;
    margin-left:5px;
}
</style>
