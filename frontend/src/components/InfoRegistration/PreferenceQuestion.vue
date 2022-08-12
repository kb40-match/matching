<template>
    <div>
        <div class="quiz-main" v-for="(element, index) in questions.slice(a,b)" :key="index">

            <v-progress-linear id="progressBar" rounded v-model="percentage" height="30" color="#7048e8" >
                <strong style="color: white;"> {{ percentage }}%</strong>
            </v-progress-linear>
                
            <div class="box-question" style="margin: 20px">
                <h1 id="questionText"> {{ element.question }}</h1>
            </div>
            <div id="box-answers" style="padding-bottom: 50px;">
                <v-btn id="box-answer" v-on:click="selectLeftAnswer" v-bind:color="clickedLeft ? '#666666' : '#F3F4F6'" > {{ element.suggestions[0].suggestion }} </v-btn>
                <v-btn id="box-answer" v-on:click="selectRightAnswer" v-bind:color="clickedRight ? '#666666' : '#F3F4F6'" > {{ element.suggestions[1].suggestion }} </v-btn>                
            </div>
        </div>

        <div style="text-align: center;"> 
            <v-btn block id="nextBtn" @click="goNextQuestion" color="#7048e8" rounded style="height: 50px">다음</v-btn>
        </div>
    </div>

</template>

<script>
export default {
    data() {
        return {
            a: 0,
            b: 1,
            initial: 0,
            clickedLeft: false,
            clickedRight: false,
            questions: [
                {
                    question: "당신은 당신의 반려동물과 모르는 사람 중 하나만 살릴 수 있다. 당신은 누구를 살릴 것인가? ",
                    suggestions: [
                        { suggestion: "반려동물"},
                        { suggestion: "모르는 사람"}
                    ]
                },
                {
                    question: "당신은 외향형 입니까? 내향형 입니까?",
                    suggestions: [
                        { suggestion: "외향형"},
                        { suggestion: "내향형"}
                    ]
                },
                {
                    question: "당신은 외향형 입니까? 내향형 입니까?",
                    suggestions: [
                        { suggestion: "외향형"},
                        { suggestion: "내향형"}
                    ]
                },
                {
                    question: "당신은 외향형 입니까? 내향형 입니까?",
                    suggestions: [
                        { suggestion: "외향형"},
                        { suggestion: "내향형"}
                    ]
                },
                {
                    question: "당신은 외향형 입니까? 내향형 입니까?",
                    suggestions: [
                        { suggestion: "외향형"},
                        { suggestion: "내향형"}
                    ]
                }
            ]
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
            if(this.b >= this.questions.length) {
                this.$router.push("/").catch(() => {}); // FIXME: path change
            } else {
                this.a++;
                this.b++;
            }
        }
    },
    computed: {
        percentage: function () {
            return parseInt(this.a / this.questions.length * 100);
        }
    }
}
</script>

<style lang="scss" scoped>
@use '../../styles/constants/colors.scss';

#progressBar {
    margin-top: 80px;
}

#questionText{
    text-align: center;
    margin-top: 50px;
    padding-bottom:50px;
    font-size: 24px;
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
    border: 1px solid #DDDDDD;
    border-radius: 15px;
    cursor: pointer;
}

#nextBtn {
    margin-top: 50px;
}

</style>