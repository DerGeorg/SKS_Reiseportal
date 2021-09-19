<template>
  <div>
    <md-card md-with-hover id="card">
      <md-ripple>
        <md-card-header>
          <div class="md-title">{{article.title}}</div>
          <div class="md-subhead">Written by: {{article.author}}</div>
        </md-card-header>

        <md-card-content>
          {{article.text}}
        </md-card-content>

        <md-card-actions>
          <p>{{ article.pubDate }}</p>
        </md-card-actions>
      </md-ripple>
    </md-card>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ArticleView",
  data(){
    return{
      article: {}
    }
  },
  methods:{
    getData(){
      axios.get('http://localhost:8080/api/article/'+ this.$route.params.id).then(response => {
        // handle success
        this.article = response.data;
      })
          .catch(error => {
            // handle error
            alert(error);
          })
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<style scoped>

#card{
  margin-left: 10%;
  margin-right: 10%;
}
</style>