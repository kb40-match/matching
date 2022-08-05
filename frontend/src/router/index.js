import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../components/Main'
import FaceSelect from '../views/face-matching/FaceSelect'
import FaceFinal from '../views/face-matching/FaceFinal'
import MindQuestion from '../views/mind-matching/MindQuestion'

Vue.use(VueRouter);

export default new VueRouter({
    mode: "history",
    routes: [
        {
            path: "/",
            component: Main
        },
        {
            path: "/faceSelect", 
            name: "FaceSelect",
            component: FaceSelect
        },
        {
            path: "/faceFinal",
            name: "FaceFinal", 
            component: FaceFinal
        },
        {
            path: "/mindQuestion",
            name: "MindQuestion",
            component: MindQuestion
        }
    ]
})