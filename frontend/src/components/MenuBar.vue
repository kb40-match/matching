<template>
  <div>
    <v-navigation-drawer
        v-if="!$vuetify.breakpoint.smAndUp"
        v-model="drawer"
        :clipped="$vuetify.breakpoint.lgAndUp"
        app
        color="primary"
        dark
    >
      <v-list color="primary" nav>
        <v-list-item
            v-for="(item, i) in btnItems"
            :key="`k1-${i}`"
            :href="item.href"
            :target="item.target"
            :to="item.to"
            link
        >
          <v-list-item-content>
            <v-list-item-title>{{ item.text }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
            v-for="(item, i) in barItems"
            :key="`k2-${i}`"
            :href="item.href"
            :target="item.target"
            :to="item.to"
            link
        >
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
        v-if="this.store.user"
        :clipped-left="$vuetify.breakpoint.lgAndUp"
        app
        color="#845ef7"
        elevate-on-scroll
        flat
        style="padding-top:10px;height:73px !important;"
    >    <!-- margin-top:27px; -->
      <v-container :class="{ 'px-0': !$vuetify.breakpoint.smAndUp }">
        <v-row
            :no-gutters="!$vuetify.breakpoint.smAndUp"
            align="center"
            justify="space-between"
           
        >

        <!-- Header Title -->
        <div v-if="page=='Main'" style="margin-left:5px;display: flex; align-items: center;">
        <img style="width:40px;height:40px;border-radius:100%; margin-right:10px;" :src=getImg(this.store.user.profileFilename) />
        <div style="color:white;font-size:18px;">{{this.store.user.nickname}} 님</div>
        </div>

        <div v-if="page=='MyDataAgree'" style="margin-left:5px;display: flex; align-items: center;">
        <!-- <v-icon dark large>mdi-chevron-left</v-icon> -->
        <div style="color:white; font-size:18px;margin-left:10px;">약관 동의</div>
        </div>

        <div v-if="page=='BasicInfoList'" style="margin-left:5px;display: flex; align-items: center;">
        <!-- <v-icon dark large>mdi-chevron-left</v-icon> -->
        <div style="color:white; font-size:18px;margin-left:10px;">기본정보 등록</div>
        </div>

        <div v-if="page=='PreferenceQuestion'" style="margin-left:5px;display: flex; align-items: center;">
        <!-- <v-icon dark large>mdi-chevron-left</v-icon> -->
        <div style="color:white; font-size:18px; margin-left:10px;">성향 파악</div>
        </div>

        <div v-if="page=='RequestList'" style="margin-left:5px;display: flex; align-items: center;">
        <v-icon dark large>mdi-chevron-left</v-icon>
        <div style="color:white; font-size:18px; margin-left:10px;">매칭 요청목록</div>
        </div>

        <div v-if="page=='FaceSelect'" style="margin-left:5px;display: flex; align-items: center;">
        <v-icon dark large>mdi-chevron-left</v-icon>
        <div style="color:white; font-size:18px; margin-left:10px;">외모로 연결</div>
        </div>

        <div v-if="page=='MindQuestion'" style="margin-left:5px;display: flex; align-items: center;">
        <v-icon dark large>mdi-chevron-left</v-icon>
        <div style="color:white; font-size:18px; margin-left:10px;">마음으로 연결</div>
        </div>

          <!-- List, Menu -->
          <v-col v-if="page=='Main' || page=='RequestList' || page=='FaceSelect' || page=='MindQuestion'"
            class="d-flex align-center" style="justify-content: flex-end;">
          <v-icon style="color:white; margin-right:5px;" @click="goRequestList">mdi-bell</v-icon>
            <v-app-bar-nav-icon
                v-if="!$vuetify.breakpoint.mdAndUp"
                @click.stop="drawer = !drawer"
                style="color:white"
            />
            <v-toolbar-title
                class="font-weight-bold text-h5 primary--text"
                style="cursor: pointer"
                @click="$router.push('/')"
            >
              
            </v-toolbar-title>
          </v-col>

          <v-col v-if="$vuetify.breakpoint.mdAndUp" cols="6">
            <v-btn
                v-for="(item, i) in barItems"
                :key="`k3-${i}`"
                :to="item.to"
                class="text-capitalize"
                exact
                exact-active-class="accent--text"
                text
            >{{ item.title }}
            </v-btn
            >
          </v-col>

          <v-col v-if="$vuetify.breakpoint.mdAndUp" class="text-right">
            <v-btn
                v-for="(item, i) in btnItems"
                :key="`k4-${i}`"
                :color="item.color"
                :href="item.href"
                :outlined="item.outlined"
                :target="item.target"
                :to="item.to"
                class="ml-3 text-capitalize"
            >
              <v-icon left>{{ item.icon }}</v-icon>
              {{ item.text }}
            </v-btn>
          </v-col>
        </v-row>
        
      </v-container>
      
    </v-app-bar>
  </div>
</template>

<script>
import { useAppStore } from '../store/userState'

export default {
  name: 'MenuBar',
  setup(){
        const store = useAppStore()
        return {store}
  },
  props:{
    page : String
  },
  data: () => ({
    drawer: null,
    btnItems: [
      {
        text: "연애조작단",
        href: "https://github.com/kb40-match",
        target: "_black",
        color: "primary",
        icon: "mdi-download",
      },
    ],
    barItems: [
      {
        title: "이니",
        to: "/",
      },
      {
        title: "우니",
        to: "/category",
      },
      {
        title: "워니",
        to: "/detail",
      },
      {
        title: "영이",
        to: "/authors",
      },
      {
        title: "혀니",
        to: "/login",
      },
    ],
  }),
  methods:{
    getImg(filename){
      return require("@/assets/"+filename)
    },
    goRequestList(){
      this.$router.push("/requestList").catch(() => {});
    }
  },
  computed: {
        user : function() {
            return this.store.user
        }
    }
};
</script>

<style>
.v-toolbar__content{
  padding:0px;
}
</style>