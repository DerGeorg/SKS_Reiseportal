<template>
    <div class="stats">
          <md-field id="filter">
            <md-select v-model="statType" id="statType" namen="statType">
                <md-option value="month">This month</md-option>
                <md-option value="all">All articles</md-option>
            </md-select>
          </md-field>
        <div class="md-layout md-gutter md-alignment-center">
            <div class="md-layout-item md-medium-size-33 md-small-size-50 md-xsmall-size-100" v-for="(stat, a) in this.orderedStats" v-bind:key="a">
                <StatComponent @click.native="openArticle(stat.id)" v-bind:stat="stat"></StatComponent>
            </div>
        </div>
    </div>
</template>

<script>
    import StatComponent from "@/components/StatComponent";
    import axios from "axios";
    import _ from 'lodash';

    export default {
        name: "StatsView",
        data(){
            return {
                stats:{},
                statType: ""
            };
        },
        components: {
            StatComponent
        },
        methods: {
            getDataMonth(){
                axios.get('http://localhost:5555/statsserver/stats/month')
                    .then(r=> {this.stats = r.data})
                    .catch(error => alert(error));
            },
            getDataAll(){
                axios.get('http://localhost:5555/statsserver/stats/all')
                    .then(r=> {this.stats = r.data})
                    .catch(error => alert(error));
            },
          openArticle(id){
            this.$router.push({path:'/article/' + id})
          }
        },
        mounted(){
            this.statType = "all";
        },
        watch: {
            statType:function () {
                if(this.statType === "all"){
                    this.getDataAll();
                }else {
                    this.getDataMonth();
                }
            }
        },
        computed: {
            orderedStats(){
                return _.orderBy(this.stats, ['count'], 'desc');
            }
        }
    }
</script>

<style scoped>
    #filter{
        margin-left: auto;
        margin-right: auto;
        width: 10%;
        margin-bottom: 50px;
    }
</style>
