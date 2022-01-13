<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <!--<HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <div class="md-layout md-gutter md-alignment-center">
      <div class="md-layout-item md-xlarge-size-30 md-large-size-35 md-medium-size-40 md-small-size-45 md-xsmall-size-100" v-for="(article, a) in this.articles" v-bind:key="a">
        <ArticleComponent style="min-height: 20em" @click.native="openArticle(article.id)" v-bind:article="article"></ArticleComponent>
      </div>
    </div>
  </div>
</template>

<style>
.md-layout-item {
  margin: 1%;
}

</style>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'
import ArticleComponent from "@/components/ArticleComponent";
import axios from "axios";

export default {
  name: 'Home',
  components: {
    ArticleComponent
  },
  data(){
    return {
      articles: []
    }
  },
  methods: {
    getData(){
      axios.get('http://localhost:5555/articleserver/articles').then(response => {
        // handle success
       this.articles = response.data;
      })
      .catch(error => {
        // handle error
        alert(error);
      })
    },
    openArticle(id){
      this.$router.push({path:'/article/' + id})
    }
  },
  mounted(){
     this.getData();
  }
}
</script>
