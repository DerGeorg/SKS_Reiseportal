<template>
<div >


  <h1>Add Article</h1>

  <md-table id="table">
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
      axios.get('http://localhost:8061/api/sightseeings').then(res => {
        this.sightseeings = res.data;
      }).catch(err => alert(err));
    },
    createArticle(){
      var titleUri = encodeURIComponent(this.title);
      var authorUri = encodeURIComponent(this.author);
      var textUri = encodeURIComponent(this.text);
      var sightseeingIDUri = encodeURIComponent(this.selectedSightseeingId);
      axios.post('http://localhost:8080/api/article?title='+titleUri+'&author='+authorUri+'&text='+textUri+'&sightseeingID='+sightseeingIDUri).then(res => { //Article Server
        alert('http://localhost:8071/api/article/'+res.data.id+'?sightseeingID='+sightseeingIDUri)


       // TODO der call an den statsserver soll weg und dafür soll der article server per kafka einen msg an stats senden um auch im stats den eintrag zu erstellen
        // Es muss im article server der return als id für den article am stats server genommen werden und die sightseeingidUri wurde schon beim ersten call als param geschickt
        axios.post('http://localhost:8071/api/article/'+res.data.id+'?sightseeingID='+sightseeingIDUri).then(() => window.location.replace("http://localhost:8081/")).catch(err => alert(err)); //statsServer
      }).catch(error => alert(error));

    }
  },
  mounted() {
    this.getSightseeingData();
  }
}
</script>

<style scoped>

#table{
  margin-left: 10%;
  margin-right: 10%;
}
</style>