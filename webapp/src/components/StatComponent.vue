
<template>
    <div>
        <md-card md-with-hover>
            <md-ripple>
                <md-card-header>
                    <div class="md-title">{{article.title}}</div>
                    <div class="md-subhead">Written by: {{article.author||'unknown'}}</div>
                </md-card-header>

                <md-card-content>
                    This article was written about {{sightseeing.name||'unknown'}} and has been {{stat.count||'undefined'}} times read.
                </md-card-content>
            </md-ripple>
        </md-card>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "StatComponent",
        data(){
            return {
                article: {},
                sightseeing: {}
            }
        },
        props: {
            stat: Object
        },
        methods:{
            getArticle(){
                axios.get(`http://localhost:5555/articleserver/article/${this.stat.id}?doNotCount=true`)
                    .then(response => this.article = response.data)
                    .catch(error => alert(error));
            },
            getSightseeing(){
                axios.get('http://localhost:5555/sightseeingserver/sightseeing/' + this.stat.sightseeingID)
                    .then(response => this.sightseeing = response.data)
                    .catch(error => alert(error));
            }
        },
        mounted() {
            this.getArticle();
            this.getSightseeing();
        }
    }
</script>

<style scoped>

</style>
