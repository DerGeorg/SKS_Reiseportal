import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AddArticle from "@/views/AddArticle";
import ArticleView from "@/views/ArticleView";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/article',
    name: 'Add Article',
    component: AddArticle
  },
  {
    path: '/article/:id',
    component: ArticleView,
    name: 'Article'
  }
]


const router = new VueRouter({
  routes
})

export default router
