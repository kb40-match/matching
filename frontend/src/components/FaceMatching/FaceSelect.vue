<template>
<v-container>
<div style="text-align:center;">
    <div id = "title">
        <h1 v-if="stage==2">결승</h1>
        <h1 v-else>{{stage}} 강전</h1>
        <h3>마음이 가는 사용자를 선택해주세요</h3>
    </div>
    <div id = "face-img">
    <img :src="getProfile(this.save[this.round])" @click="selectT" :class="{'selected':isTop, 'no-selected':!isTop}" />
    <h2>VS</h2>
    <img :src="getProfile(this.save[this.round+1])" @click="selectB" :class="{'selected':isBottom, 'no-selected':!isBottom}"/>
    </div>
    <v-btn id="nextBtn" rounded text large dark @click="goNext">{{this.btnText}}</v-btn>
    <v-snackbar v-model="alert" Bottom flat color="black" rounded="pill" :timeout="1500">
      <span class="snackText">
        한명을 선택해주세요
      </span>
    </v-snackbar>
</div>
</v-container>
</template>

<script>
export default {
    data(){
        return{
            alert: false,
            btnText : "다음",
            stage : 8,
            round : 0,
            isTop : false,
            isBottom : false,
            users : [
                {user_id:"user1", nickname: "이니이니1", profile_filename : "jaein1.png"},
                {user_id:"user2", nickname: "이니이니2", profile_filename : "jaein2.png"},
                {user_id:"user3", nickname: "이니이니3", profile_filename : "jaein3.png"},
                {user_id:"user4", nickname: "이니이니4", profile_filename : "jaein4.png"},
                {user_id:"user5", nickname: "이니이니5", profile_filename : "jaein5.png"},
                {user_id:"user6", nickname: "이니이니6", profile_filename : "jaein6.png"},
                {user_id:"user7", nickname: "이니이니7", profile_filename : "jaein7.png"},
                {user_id:"user8", nickname: "이니이니8", profile_filename : "jaein8.png"}
            ],
            save : [0,1,2,3,4,5,6,7]
        }
    },
    
    methods: {
        selectT() {
            this.isTop = true;
            this.isBottom = false;
        },
        selectB() {
            this.isTop = false;
            this.isBottom = true;
        },
        goNext() {
            if(!this.isTop && !this.isBottom){
                this.alert = true;
                return;
            }
            
            if(this.isTop){ // 위에 선택하면
                this.save.push(this.save[this.round])
            }else{ // 아래 선택하면
                this.save.push(this.save[this.round+1])
            }

            this.round += 2
            
            if(this.round >=this.stage){
                this.round = 0
                this.save = this.save.slice(this.stage)
                this.stage = this.stage / 2
            }

            if(this.round+2 >= this.stage){
                this.btnText = "다음 라운드"
            }else{
                this.btnText = "다음"
            }

            this.isTop = false
            this.isBottom = false

            if(this.stage == 2){
                this.btnText = "결과보기"
            }

            if(this.stage == 1){ // 상세 페이지로
                let finalUser = this.users[this.save[0]]
                this.$router.push({name: 'FaceFinal', params: {user: finalUser}})
            }
        },
        getProfile(i){
            return require("@/assets/" + this.users[i].profile_filename)
        },
        setIdx(){
            // for(const key in this.users){
            //     // this.users[key].push()
            //     console.log(key)
            // }
            this.users = this.users.map((user, idx) => ({
                ...user, 
                idx,
            }))
            console.log(this.users)
        }
    },
    mounted(){
        this.setIdx()
    }
}
</script>


<style scoped>
#title{
    text-align:center;
    margin-top:10px;
}

#face-img{
    text-align:center;
    margin : 30px;
}

#nextBtn{
    background-color:#8452f7;
    width:70%;
}

img {
    width:160px;
    height:160px;
    border: 5px solid #f3f0ff;
    border-radius: 50%;
     /* filter: brightness(50%); */
     /* opacity:0.2; */
}

.selected{
border: 5px solid #8452f7;
}

.no-selected{
border: 5px solid #f3f0ff;
}
</style>
