<template>
    <v-container style="height: 100%; text-align:center;">
        <!-- <div style="text-align:center;"> -->
            <div style="height:20%; padding-top:40px; text-align:center; display:flex;justify-content: center;">
                <v-progress-linear
                    v-model="percent"
                    color="#8452f7"
                    height="30"
                    rounded
                    dark
                    style="width:90%; border-radius:20px;"
                >
                    <template v-slot:default="{ value }">
                        <strong>{{ Math.ceil(value) }}%</strong>
                    </template>
                </v-progress-linear>
            </div>
            <div style="height:60%; padding-top:10%;">
                <div class="mb-10">
                    <h1>나의 {{items[num]}} </h1>
                </div>
                <div style="display:flex; justify-content: center; align-items: center;">
                    <input v-if="num==0" id="text-form" v-model="user.nickname" placeholder="닉네임을 입력하세요.">
                    <div v-if="num==1" id="disable-form">{{mydata.age}} </div> <span v-if="num==1">세</span>
                    <input v-if="num==2" id="text-form" v-model="user.job" placeholder="직업을 입력하세요.">
                    <div v-if="num==3" style="display:flex">
                        <v-col>
                        <v-row><span>1위</span> <div id="consume-form">{{mydata.consume_ptn1}}</div></v-row>
                        <v-row style="margin-top:30px;"><span>2위</span> <div id="consume-form">{{mydata.consume_ptn2}}</div></v-row>
                        <v-row style="margin-top:30px;"><span>3위</span> <div id="consume-form">{{mydata.consume_ptn3}}</div></v-row>
                        </v-col>
                    </div>
                    <div v-if="num==4" id="disable-form">{{addComma(mydata.assets)}} </div> <span v-if="num==4">원</span>
                    <div v-if="num==5" id="disable-form">{{mydata.invest_prop}} </div>
                    <div v-if="num==6" style="width:250px;" id="disable-form">
                        <v-select v-model="user.hobby" :items="hobbyItems" placeholder="취미를 선택하세요." style="font-size:20px; ">
                        </v-select>
                    </div>
                </div>
            </div>
            <div style="height:20%">
                <v-btn text rounded dark large @click="goPre" class="mr-7" v-if="num!=0" style="background-color:#8452f7; width:40%; font-size:20px;">이전</v-btn>
                <v-btn text rounded dark large @click="goNext" style="background-color:#8452f7; width:40%; font-size:20px;" :disabled=check()>{{btnText}}</v-btn>
            </div>
        <!-- </div> -->
    </v-container>
</template>

<script>
// import axios from 'axios'

export default {
    data() {
        return{
            user_id : "user1",
            percent : 0,
            user:{
                nickname : "",
                job : "",
                hobby:""
            },
            mydata:{
                age : "20",
                consume_ptn1 : "식비", 
                consume_ptn2 : "월세", 
                consume_ptn3 : "의류비",
                assets : "2000000000",
                invest_prop : "공격적"
            },
            hobbyItems : ["스포츠/피트니스", "어학", "재테크/투자", "요리/음료/맛집", "교양/전시회", "게임/코딩/프로그래밍", "음악/악기", "사진/영상제작", "패션/뷰티", "공에/DIY", "미술/캘리그라피", "연기/춤", "기타"],
            num : 0,
            items : ["닉네임은", "나이는", "직업은", "소비패턴TOP3는", "총 자산은", "투자성향은", "취미는"],
            btnText : "다음"
        }
    },
    methods:{
        goPre(){
            this.num = this.num - 1
            this.percent = 100 / this.items.length * this.num
            if(this.num == this.items.length -2){
                this.btnText = "다음"
            }
        },
        goNext(){
            if(this.num == this.items.length-1){ // 성향 파악으로 이동
                this.$axios.put(`/user`, this.user)
                .then((response) => {
                    console.log("업데이트 완료")
                    console.log(response.data)
                })
                .catch((err)=>{
                    console.log(err.response);
                });
                console.log("끝")
                console.log(this.user)
                return
            }

            this.num = this.num + 1
            this.percent = 100 / this.items.length * this.num
            if(this.num == this.items.length-1){
                this.btnText = "등록 완료"
            }
        },
        check(){
            if(this.num == 0){
                if (this.user.nickname == "") return true
                else return false
            }else if(this.num == 2){
                if (this.user.job == "") return true
                else return false
            }else if(this.num == 6){
                if (this.user.hobby == "") return true
                else return false
            }else{
                return false
            }
        },
        addComma(price) {
            price = price + ""
            var regexp = /\B(?=(\d{3})+(?!\d))/g
            return price.toString().replace(regexp, ',')
        },
    }
}
</script>

<style scoped>
#text-form{
    border:2px solid;
    border-radius: 20px;
    height:70px;
    text-align: center;
    font-size:30px;
    background-color: #f3f0ff;
}

#consume-form{
    border: 2px solid;
    border-radius: 20px;
    font-size:30px;
    font-weight:bold;
    width:200px;
    margin-left:10px;
    background-color: #f3f0ff;
}

span{
    font-size:30px;
    font-weight:bold
}

#disable-form{
    border: 2px solid;
    border-radius: 20px;
    font-size:30px;
    font-weight:bold;
    padding:10px;
    margin-right:10px;
    width:240px;
    background-color: #f3f0ff;
}

#hobby-form{
    font-size:30px;
}

.v-select__selection--comm {
    overflow : visible !important;
}
</style>