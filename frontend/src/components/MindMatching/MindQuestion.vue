<template>
    <div>
        <MenuBar page="MindQuestion" />
        <v-dialog v-model="loading" fullscreen>
            <v-container fluid fill-height style="background-color: rgba(255, 255, 255, 0.5);">
                <v-layout justify-center align-center>
                <v-progress-circular
                    indeterminate
                    color="primary">
                </v-progress-circular>
                </v-layout>
            </v-container>
        </v-dialog>

        <div class="quiz-main" v-for="(element, index) in questionList.slice(a,b)" :key="index" style="margin: 20px">
            <div id="steps">
                <v-avatar v-if="a >= 1" color="#845ef7" style="margin-right:10px;"> <v-icon dark> mdi-check </v-icon></v-avatar>
                <v-avatar v-else id="step1" color="#DDDDDD" style="margin-right:10px; ">1</v-avatar>

                <v-avatar v-if="a >= 2" color="#845ef7" style="margin-right:10px;"> <v-icon dark> mdi-check </v-icon></v-avatar>
                <v-avatar v-else id="step2" color="#DDDDDD" style="margin-right:10px;">2</v-avatar>

                <v-avatar v-if="a >= 3" color="#845ef7" style="margin-right:10px;"> <v-icon dark> mdi-check </v-icon></v-avatar>
                <v-avatar v-else id="step3" color="#DDDDDD" style="margin-right:10px;">3</v-avatar>
            </div>

            <h1 id="guideText">매칭 전 몇 가지만 확인할게요!</h1>
            
            <div style="height:200px;">
                <h1 id="questionText"> {{ element.questionContent }}</h1>
            </div>
            <div id="box-answers" style="padding-bottom: 50px;">
                <div id="box-answer" @click="selectLeftAnswer" :class="{'selected':clickedLeft,'no-selected':!clickedLeft}" > {{ element.answerContent1 }} </div>
                <div id="box-answer" @click="selectRightAnswer" :class="{'selected':clickedRight,'no-selected':!clickedRight}" > {{ element.answerContent2 }} </div>
            </div>
            <div style="text-align: center;"> 
            <v-btn text @click="goNextQuestion" rounded dark style="height: 50px; font-size:24px;width:250px; background-color: #845ef7;">다음</v-btn>
        </div>
        </div>
        
    </div>
</template>

<script>
import { useAppStore } from '../../store/userState'
import { loadResultFromAI } from '../../worker/user';
import MenuBar from "../MenuBar.vue";

export default {
    setup() {
        const store = useAppStore();
        return { store }
    },
    components : {
        MenuBar
    },
    data() {
        return {
            a: 0,
            b: 1,
            loading: false,
            clickedLeft: false,
            clickedRight: false,
            questionList: [],
        }
    },
    methods: {
        selectLeftAnswer() {
            this.clickedLeft = !this.clickedLeft
            if(this.clickedRight) {
                this.clickedRight = !this.clickedRight
            }
        },
        selectRightAnswer() {
            this.clickedRight = !this.clickedRight
            if(this.clickedLeft) {
                this.clickedLeft = !this.clickedLeft
            }
        },

        goNextQuestion() {
            this.clickedLeft = false
            this.clickedRight = false
            if(this.b >= 3) {
                loadResultFromAI("user5")  // get 5 user and myData objects
                this.loading = true

                setTimeout(() => {
                    this.loading = false
                    this.$router.push("/mindMatchingList").catch(() => {});
                }, 3000);
            } 
            else {
                this.a++;
                this.b++;
            }
        }
    },
    beforeMount() {
        this.$axios
                .get(`/question/findAll`)
                .then((response) => {
                    this.questionList = response.data
                    
                })
                .catch((err) => {
                console.log(err.response);
                });
    }
}
</script>

<style lang="scss" scoped>

#steps {
    padding-top:40px;
    padding-left:10px;
    padding-bottom:40px;
    font-size: 16px;
    color: white;
}

#step1 {
    color: white;
}

#step2 {
    color: white;
}

#step3 {
    color: white;
}

#guideText{
    text-align: center;
    font-weight: normal;
    font-size: 20px;
    padding-bottom:30px;
}

#questionText{
    text-align: center;
    padding-bottom:30px;
    font-size: 24px;
}

#box-answers {
    text-align: center;
    margin-bottom: 30px;
    // padding-top: 50px;
    display:flex; 
    justify-content: center;
}

#box-answer {
    // border: 1px solid colors.$GRAY0;
    width:170px;
    height:170px;
    margin-left: 5px;
    margin-right: 5px;
    border: 1px solid ;
    // border-radius: 15px;
    word-break:break-all;
    font-size:20px;
    padding:2px;
    display:flex;
    align-items:center;
    justify-content:center;
}

#nextBtn {
    color:white;
    font-size: 24px;
}
.selected{
    background-color:#666666;
}
.no-selected{
    background-color:#f3f0ff;
}
</style>
