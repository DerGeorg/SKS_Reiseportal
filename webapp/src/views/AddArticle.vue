<template>
<div >


  <h1>Add Article</h1>

  <md-table>
    <md-table-row>
      <md-table-cell>
        <md-label for="name">Titel</md-label>
      </md-table-cell>
      <md-table-cell>
        <md-field>
        <md-input type="text" id="title" v-model="title"></md-input>
        </md-field>
      </md-table-cell>
    </md-table-row>
    <md-table-row>
      <md-table-cell>
        <md-label for="author">Author</md-label>
      </md-table-cell>
      <md-table-cell>
        <md-field>
        <md-input type="text" id="author" v-model="author"></md-input>
        </md-field>
      </md-table-cell>
    </md-table-row>
    <md-table-row>
      <md-table-cell>
        <md-label for="text">Text</md-label>
      </md-table-cell>
      <md-table-cell>
        <md-field>
        <md-textarea id="text" v-model="text"></md-textarea>
        </md-field>
      </md-table-cell>
    </md-table-row>
    <md-table-row>
      <md-table-cell>
        <md-label for="sightseeing">Sightseeing Attraction</md-label>
      </md-table-cell>
      <md-table-cell>
        <md-field>
          <md-select v-model="selectedSightseeingId" id="sightseeing">
            <md-option v-for="(sightseeing, s) in this.sightseeings" v-bind:key="s" v-bind:value="sightseeing.id">{{sightseeing.name}}</md-option>
          </md-select>
        </md-field>
      </md-table-cell>
    </md-table-row>
  </md-table>
  <md-button class="md-raised md-primary" @click="createArticle">Create Article now</md-button>
</div>
</template>

<script>
import axios from "axios";
export default {
  name: "AddArticle",
  data(){
    return{
      sightseeings: [],
      selectedSightseeingId: "",
      title: "",
      text: "",
      author: "",
      newArticle: {}
    }
  },
  methods: {
    getSightseeingData(){
      axios.get('http://localhost:5555/sightseeingserver/sightseeings').then(res => {
        this.sightseeings = res.data;
      }).catch(err => alert(err));
    },
    createArticle(){
      var titleUri = encodeURIComponent(this.title);
      var authorUri = encodeURIComponent(this.author);
      var textUri = encodeURIComponent(this.text);
      var sightseeingIDUri = encodeURIComponent(this.selectedSightseeingId);
      axios.post('http://localhost:5555/articleserver/article?title='+titleUri+'&author='+authorUri+'&text='+textUri+'&sightseeingID='+sightseeingIDUri).then(() => { //Article Server
        window.location.replace("http://localhost:8081/")
      }).catch(error => alert(error));

    }
  },
  mounted() {
    this.getSightseeingData();
  }
}
</script>

<style scoped>

</style>