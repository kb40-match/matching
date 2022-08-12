<template>
    <v-container>
        <div id="user-img">
            <img src="@/assets/logo.png" />
        </div>
        <div id="user-info">
            <!-- <div style="text-align:center;">
                <v-btn id="side-btn" text><v-icon large>mdi-close</v-icon></v-btn>
                <v-btn dark id="btn" text><v-icon large>mdi-crown</v-icon></v-btn>
                <v-btn id="side-btn" text><v-icon large>mdi-chat</v-icon></v-btn>
             </div> -->

             <div style="margin:20px; display:flex;align-items:center;">
                <div style="width:250px;">
                    <div style="font-size:20px;font-weight:bold;">{{user.nickname}}, {{mydata.age}}세</div>
                    <div style="font-size:15px;"><v-icon small style="color:black;">mdi-map-marker</v-icon>{{mydata.address}}</div>
                </div>
                <div id="chat" @click="goChat">
                    <v-icon large style="color:white">mdi-chat-outline</v-icon>
                </div>
             </div>
             
             <div style="margin:20px;margin-top:30px;">
                <h3><v-icon class="mr-2" color=black>mdi-face-man</v-icon>About</h3>
                <div style="display:flex; margin-top:5px;">
                    <div id="items" style="width:30%;">
                        <p>직업</p> 
                        <p>MBTI</p> 
                        <p>반려동물</p> 
                        <p>소비패턴</p> 
                        <p>자산</p> 
                        <p>투자성향</p> 
                    </div>
                    <div>
                        <p>{{user.job}}</p> 
                        <p>{{user.mbti_mind}}{{user.mbti_recog}}{{user.mbti_judge}}{{user.mbti_tactics}}</p> 
                        <p v-if="user.pet_prefer=='Y'">선호</p> <p v-else>비선호</p>
                        <p>1.{{mydata.consume_ptn1}} 2.{{mydata.consume_ptn2}} 3.{{mydata.consume_ptn3}}</p> 
                        <p>{{addComma(mydata.assets)}} 원</p> 
                        <p>{{mydata.invest_prop}}</p>
                    </div>
                </div>
                <div style="margin-top:20px;">
                    <h3><v-icon class="mr-2" color=black>mdi-heart-box-outline</v-icon>Interest</h3>
                    <div style="margin-top:5px; display:flex;" >
                        <v-chip class="mr-2" small color="#7950f2" dark v-for="(value, index) in user.hobby" :key="value">{{user.hobby[index]}}</v-chip>
                    </div>
                </div>
             </div>
        </div>
    </v-container>
</template>
<script>

export default {
    data() {
        return{
            
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
            console.log(this.userId)
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
#user-img{
    border-bottom-left-radius: 30px;
    border-bottom-right-radius: 30px;
    /* border: 1px solid; */
    height:200px;
    background-color:#f3f0ff;
    display:flex;
    align-items: center;
    justify-content:center;
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
