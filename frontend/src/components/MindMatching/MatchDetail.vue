<template>
    <v-container>
        <MenuBar page="FaceSelect" />
        <div id="user">
            <div id="user-img">
                <img :src=getProfile(user.profileFilename) style="width:180px; height:180px; border-radius:100%;" />
            </div>
            <div style="margin:10px;padding:20px; display:flex; align-items:center; background-color:#f3f0ff; border-radius:20px;">
                <div style="width:210px;">
                    <div style="font-size:21px;font-weight:bold;">{{user.nickname}}, {{mydata.age}}세</div>
                    <div style="font-size:15px;"><v-icon small style="color:black;">mdi-map-marker</v-icon>{{mydata.address}}</div>
                </div>
                <div id="chat" @click="goChat">
                    <v-icon style="color:white">mdi-chat</v-icon>
                </div>
             </div>
        </div>
        <div id="user-info">
            <!-- <div style="text-align:center;">
                <v-btn id="side-btn" text><v-icon large>mdi-close</v-icon></v-btn>
                <v-btn dark id="btn" text><v-icon large>mdi-crown</v-icon></v-btn>
                <v-btn id="side-btn" text><v-icon large>mdi-chat</v-icon></v-btn>
             </div> -->
             <div style="margin:20px;">
             <hr />
                <h3><v-icon class="mr-2" color=black>mdi-face-man</v-icon>About</h3>
                <div style="display:flex; margin-top:5px;margin-bottom:20px;">
                    <div id="items" style="width:35%;">
                        <p>직업</p> 
                        <p>MBTI</p> 
                        <p>반려동물</p> 
                        <p>소비패턴</p> 
                        <p style="color:white">소비패턴</p> 
                        <p style="color:white">소비패턴</p> 
                        <p>자산</p> 
                        <p>투자성향</p> 
                    </div>
                    <div>
                        <p>{{user.job}}</p> 
                        <p>{{user.mbtiMind}}{{user.mbtiRecog}}{{user.mbtiJudge}}{{user.mbtiTactics}}</p> 
                        <p v-if="user.petPrefer=='Y'">선호</p> <p v-else>비선호</p>
                        <p>1. {{mydata.consumePtn1}} </p>
                        <p>2. {{mydata.consumePtn2}}</p> 
                        <p>3. {{mydata.consumePtn3}}</p> 
                        <p>{{addComma(mydata.assets)}} 원</p> 
                        <p>{{mydata.investProp}}</p>
                    </div>
                </div>
                <hr />
                <div style="margin-top:10px;margin-bottom:20px;">
                    <h3><v-icon class="mr-2" color=black>mdi-heart-box-outline</v-icon>Interest</h3>
                    <div style="margin-top:5px; display:flex;" >
                        <v-chip class="mr-2" color="#7950f2" v-for="(value, index) in hobbies" :key="value"><span style="color:white">{{hobbies[index]}}</span></v-chip>
                    </div>
                </div>
                <hr />
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
    data() {
        return{
            hobbies:[],
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
            type: Object
        },
        mydata:{
            type: Object
        }
    },
    methods :{
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
        addComma(price) {
            price = price + ""
            var regexp = /\B(?=(\d{3})+(?!\d))/g
            return price.toString().replace(regexp, ',')
        },
        getProfile(i){
            return require("@/assets/" + i)
        }
    },
    mounted(){
        this.hobbies = this.user.hobby.split('/')
    }
}
</script>

<style scoped>

#user{
    border : 2px solid #8452f7;
    border-radius: 30px;
    margin : 20px;
    height : 310px;
}
#user-img{
    /* border-bottom-left-radius: 30px;
    border-bottom-right-radius: 30px; */
    /* border: 1px solid; */
    height:190px;
    /* background-color:#f3f0ff; */
    display:flex;
    align-items: center;
    justify-content:center;
    /* border-radius:30px; */
}

#side-btn{
    border-radius:100%;
    width:60px;
    height:60px;
    background-color:#f3f0ff;
    margin:5px;
}

#btn{
    border-radius:100%;
    width:70px;
    height:70px;
    background-color:#7950f2;
    margin:5px;
}

#items>p{
    margin:0px !important;
    font-weight:bold;
}

p{
    margin:0px !important;
    font-size:18px;
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
    margin:0px !important;
}

h3{
    font-size:21px;font-weight:bold;margin-bottom:10px;
}
</style>
