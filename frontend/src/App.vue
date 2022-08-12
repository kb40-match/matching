<template>
<v-app>
  <MenuBar/>
  <div style="margin-top:50px; padding-top:20px;  height: calc(100% - 73px);">
  <router-view></router-view>
  </div>
</v-app>
</template>

<script>
import MenuBar from './components/MenuBar';

export default {
  name: 'App',

  components: {
    MenuBar
  },

  data: () => ({
    //
  }),
};
</script>
<style>
@font-face {
  font-family: "S-CoreDream-5Medium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-5Medium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
#app {
  font-family: "S-CoreDream-5Medium";
}
</style>