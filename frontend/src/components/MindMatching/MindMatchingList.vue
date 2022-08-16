<template>
    <div>
        <MenuBar page="MindQuestion" />
        
        <div v-if="people.length != 0" fluid style="margin-top: 30px;">
            <v-row dense id="main">
                <v-col v-for="(person, index) in people.slice(0,1)" :key="index" cols="12" xs="12">
                    <v-card id="mainCard">
                        <div id="percentLabel" style="background-color: #b197fc;" >{{ person.fitPercent }}% 매칭</div>
                        <div style="display:flex;width:100%;padding-left:20px;">
                            <div style="width:60%"><img id="topImage" :src="getProfile(index)" @click="goDetailPage(person.userId)"/></div>
                            <div style="width:40%; display:flex; justify-content: center; align-items: center;">
                            <v-col>
                                <v-btn rounded large style="font-size:17px;" @click="goDetailPage(person.userId)">상세정보</v-btn>
                                <v-btn rounded large style="margin-top:20px; font-size:17px;" @click="goChat(person.userId)">대화요청</v-btn>
                            </v-col>
                            </div>
                        </div>
                        <div style="display:flex; align-items:center;"><v-icon large color="#f8d459" class="mr-2">mdi-crown</v-icon><span style="font-size:25px;font-weight:bold;"> {{person.nickname}} </span><v-icon large color="#f8d459" class="ml-2">mdi-crown</v-icon></div>
                    </v-card>
                </v-col>
            </v-row>
           
            <v-row dense id="sub">
            <v-col v-for="(person, index) in people.slice(1)" :key="index" cols="6" xs="6">
                <v-card id="subCard">
                <div id="percentLabel" style="background-color: #b197fc; ">{{ person.fitPercent }}% 매칭</div>
                <img id="bottomImages" :src="getProfile(index+1)" @click="goDetailPage(person.userId)"/>
                <div rounded id="nameLabel">{{person.nickname}}</div>
                </v-card>
            </v-col>
            </v-row>
        </div>
        
        <div style="text-align: center; margin-bottom:20px;"> 
            <v-btn text dark rounded id="retryBtn" @click="retry">다시하기 (-500P)</v-btn>
        </div>

    </div>
    
</template>

<script>
import { useAppStore } from '../../store/userState'
import { fetchUser, fetchMyData } from '../../worker/user';
import MenuBar from "../MenuBar.vue";
import dayjs from "dayjs";

export default {
    components : {
        MenuBar
    },
    setup(){
        const store = useAppStore()
        return {store}
    },
    data() {
        return {
            a: 0,
            b: 1,
            finalUser: null,
            finalMyData: null,
            reponses: [],
            myDatas: [],
            people: [],
            match:{
                sender:'',
                receiver:'',
                activeFlag:'',
                createdDate:''
            }
        }
    },
    methods: {
        retry() {
            this.store.user.point -= 500  // decrease 500 point
            this.$router.push("/mindQuestion").catch(() => {});
        },
        async goDetailPage(userId) {

            // fetch user and myData object for finalUser
            this.finalUser = await fetchUser(userId)
            this.finalMyData = await fetchMyData(userId)

            this.$router.push({name: 'MatchDetail', params: {user: this.finalUser, mydata : this.finalMyData}}).catch(() => {});
        },
        getProfile(i){
            return require("@/assets/" + this.people[i].profileFilename)
        },
        goChat(userId){
            // console.log(this.store.user.userId)
            this.match.sender=this.store.user.userId
            this.match.receiver=userId
            this.match.activeFlag = '0'
            this.match.createdDate = dayjs().format("YYYYMMDDHHmmss")
            console.log(this.store.user.userId +" "+userId)
            this.$axios.post(`/matching/request`, this.match)
            .then(()=>{
                console.log("요청하였습니다.")
            }).catch((err)=>{
                console.log(err.response)
            })
        }
    },
    async beforeMount() {
        this.people = await this.store.aiResult    // assign reponse to people array
    }
}
</script>


<style scoped>

#main{
  margin : 10px;
  display : flex;
  align-items:center;
}
#sub{
  margin : 10px;
  display : flex;
  align-items:center;
}

#mainCard {
    display:flex;
    justify-content:center;
    align-items: center;
    flex-direction: column;
    color: white;
    height: 300px;
    border-radius: 20px;
    border:2px solid #b197fc; 
}

#subCard {
    display:flex;
    justify-content:center;
    align-items: center;
    flex-direction: column;
    color: white;
    height: 250px;
    border-radius: 20px;
    margin-bottom: 20px;
    border:2px solid #b197fc; 
}

#percentLabel {
    width: 85%;
    height:30px;
    display: flex;
    align-items: center;
    justify-content:center; 
    transform: translateY(-50%);
    border-radius: 0 0 20px 20px;
    color: white;
    font-size:20px;
}

#retryBtn {
    /* display : flex;
    align-items:center; */
    /* margin-top: 50px; */
    background-color: #7950f2;
    height:50px;
    font-size:18px;
}

#box-user {
    background-color: #C4C4C4;
}   

#topImage {
    height: 195px;
    width: 195px;
    object-fit: cover;
    border-radius: 100%;
    /* margin-bottom: 10px; */
}

#bottomImages {
    height: 150px;
    width: 150px;
    object-fit: cover;
    border-radius: 100%;
    margin-bottom: 10px;
}

#nameLabel {
    /* color: white;
    background-color: #7950f2; */
    width: 95%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 20px;
    font-weight:bold;
}


</style>