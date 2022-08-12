<template>
    <div>

        <div class="quiz-main" v-for="(element, index) in questionList.slice(a,b)" :key="index">
            <div id="steps">
                <v-avatar v-if="a >= 1" color="#DDDDDD" style="margin-right:10px;"> <v-icon dark> mdi-check </v-icon></v-avatar>
                <v-avatar v-else id="step1" color="#845ef7" style="margin-right:10px;">1</v-avatar>

                <v-avatar v-if="a >= 2" color="#DDDDDD" style="margin-right:10px;"> <v-icon dark> mdi-check </v-icon></v-avatar>
                <v-avatar v-else id="step2" color="#845ef7" style="margin-right:10px;">2</v-avatar>

                <v-avatar v-if="a >= 3" color="#DDDDDD" style="margin-right:10px;"> <v-icon dark> mdi-check </v-icon></v-avatar>
                <v-avatar v-else id="step3" color="#845ef7" style="margin-right:10px;">3</v-avatar>
            </div>

            <h1 id="guideText">매칭 전 몇 가지만 확인할게요!</h1>
            
            <div class="box-question">
                <h1 id="questionText" style="font-size: 24px"> {{ element.questionContent }}</h1>
            </div>
            <div id="box-answers" style="padding-bottom: 50px;">
                <v-btn id="box-answer" v-on:click="selectLeftAnswer" v-bind:color="clickedLeft ? '#666666' : '#F3F4F6'" > {{ element.answerContent1 }} </v-btn>
                <v-btn id="box-answer" v-on:click="selectRightAnswer" v-bind:color="clickedRight ? '#666666' : '#F3F4F6'" > {{ element.answerContent2 }} </v-btn>
            </div>
        </div>
        
        <div style="text-align: center;"> 
            <v-btn block id="nextBtn" @click="goNextQuestion" color="#7048e8" rounded >다음</v-btn>
        </div>
        
    </div>
</template>

<script>
export default {
    data() {
        return {
            a: 0,
            b: 1,
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
                this.$router.push("/mindMatchingList").catch(() => {});
            } else {
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
                    console.log(response.data)
                    
                })
                .catch((err) => {
                console.log(err.response);
                });
    }
}
</script>

<style lang="scss" scoped>
@use '../../styles/constants/colors.scss';

#steps {
    padding-top:50px;
    padding-left:10px;
    padding-bottom:50px;
    font-size: 16px;
    color: white;
}

#guideText{
    text-align: center;
    font-weight: normal;
    font-size: 20px;
    padding-bottom:50px;
}

#questionText{
    text-align: center;
    padding-bottom:50px;
}

#box-answers {
    text-align: center;
}

#box-answer {
    width:160px;
    height:180px;
    list-style: none;
    line-height: 2;
    margin-left: 10px;
    margin-right: 10px;
    border: 1px solid colors.$GRAY0;
    margin-bottom: 20px;
    border-radius: 15px;
}

</style>
