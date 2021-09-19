<template>
  <div>
    <md-card md-with-hover>
      <md-ripple>
        <md-card-header>
          <div class="md-title">{{article.title}}</div>
          <div class="md-subhead">Written by: {{article.author}}</div>
        </md-card-header>

        <md-card-content>
          {{article.text}}
        </md-card-content>


      </md-ripple>
    </md-card>
  </div>
</template>

<script>
export default {
  name: "ArticleComponent",
  props: {
    article: Object
  }
}
</script>

<style scoped>

</style>