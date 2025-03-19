<script lang="ts">
import { defineComponent, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

export default defineComponent({
  name: 'App',
  setup() {
    const route = useRoute();
    const router = useRouter();

    const currentRouteName = computed(() => route.name);

    const navigateTo = (routeName: string) => {
      router.push({ name: routeName });
    };

    return {
      currentRouteName,
      navigateTo
    };
  }
});
</script>

<template>
  <div class="app-container">
    <div class="tech-background">
      <div class="tech-grid"></div>
      <div class="tech-particles"></div>
    </div>

    <header class="header">
      <div class="logo-area">
        <img src="./assets/logo.png" alt="logo" class="logo" />
        <h1 class="title">消息推送平台</h1>
      </div>
      <div class="nav-tabs">
        <a-button
          type="primary"
          :class="['nav-btn', currentRouteName === 'Dashboard' ? 'active-tab' : '']"
          @click="navigateTo('Dashboard')"
        >
          <span class="tab-text">控制台</span>
          <span class="tab-glow"></span>
        </a-button>
        <div
          :class="['tab', currentRouteName === 'Channels' ? 'tab-active' : '']"
          @click="navigateTo('Channels')"
        >
          <span class="tab-text">渠道配置</span>
          <span class="tab-line"></span>
        </div>
        <div
          :class="['tab', currentRouteName === 'Tasks' ? 'tab-active' : '']"
          @click="navigateTo('Tasks')"
        >
          <span class="tab-text">任务管理</span>
          <span class="tab-line"></span>
        </div>
      </div>
      <div class="user-area">
        <a-avatar icon="user" class="avatar" />
        <span class="username">管理员</span>
        <a-button type="link">退出登录</a-button>
      </div>
    </header>

    <main class="main-content">
      <router-view v-slot="{ Component }">
        <transition name="fade" mode="out-in">
          <keep-alive>
            <component :is="Component" />
          </keep-alive>
        </transition>
      </router-view>
    </main>
  </div>
</template>
