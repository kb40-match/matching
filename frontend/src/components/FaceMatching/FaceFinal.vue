<template>
<v-container>
<div>
    <div id="user">
        <div id="user-img"><img :src=getProfile(user.profile_filename) /></div>
        <div id="user-info">
            <div style="display:flex; align-items:center;">
                <div style="width:180px;">
                <div style="font-size:20px;">{{user.nickname}}, {{mydata.age}}세</div>
                <div style="font-size:15px;"><v-icon small style="color:black;">mdi-map-marker</v-icon>{{mydata.address}}</div>
                </div>
                <div id="chat" @click="goChat">
                    <v-icon large style="color:white">mdi-chat-outline</v-icon>
                </div>
            </div>
        </div>
    </div>
    <div style="text-align:center">
        <v-btn text rounded large dark @click="displayDetail" style="font-size:15px;margin-right: 15px;">상세 정보 보기</v-btn>
        <v-btn rounded text large dark @click="reMatching" style="font-size:15px;margin-left: 15px;">다시하기(500원)</v-btn>
    </div>
</div>
</v-container>

</template>

<script>
export default {
    data(){
        return{
            mydata:{}
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
                .get(`/user/mydata/${this.user.user_id}`)
                .then((response) => {
                    this.mydata =  response.data
                })
                .catch((err) => {
                    console.log(err.response);
                });
        },
        displayDetail() {
            this.$router.push({name: 'MatchDetail', params: {user: this.user, mydata : this.mydata}})
        },
        reMatching() {
            this.$router.push("/faceSelect").catch(() => {});
        },
        goChat(){
            console.log("goChat")
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
button {
    /* background:rgb(248, 212, 89); */
    background: #8452f7;
    width:40%;
    font-size:15px;
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
    margin:20px;
    padding:20px;
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
</style>
