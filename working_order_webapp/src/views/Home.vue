<template>
  <div class="page">
    <div class="breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item
          v-for="(item, index) in $store.state.navigationStack"
          :key="index"
          :to="item.router"
        >
          {{item.name}}
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="main">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style scoped>
.page {
  width: 100%;
  height: 100%;
}
.main {
  /* margin: 15px; */
  height: 100%;
  background-color: white;
}
</style>

