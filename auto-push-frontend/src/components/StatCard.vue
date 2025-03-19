<template>
  <div class="stat-card tech-stat-card" :class="getBgClass">
    <div class="card-icon" :class="iconColor">
      <a-icon :type="icon" />
    </div>
    <div class="card-content">
      <h3 class="card-title">{{ title }}</h3>
      <div class="card-value">{{ value }}</div>
      <div class="card-trend" :class="trendType === 'up' ? 'trend-up' : 'trend-down'">
        <a-icon :type="trendType === 'up' ? 'arrow-up' : 'arrow-down'" />
        <span>{{ trend }}</span>
      </div>
    </div>
    <div class="card-glow"></div>
    <div class="card-line"></div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'StatCard',
  props: {
    title: {
      type: String,
      required: true
    },
    value: {
      type: String,
      required: true
    },
    trend: {
      type: String,
      default: '0%'
    },
    trendType: {
      type: String,
      default: 'up',
      validator: (value: string) => ['up', 'down'].includes(value)
    },
    icon: {
      type: String,
      default: 'info-circle'
    }
  },
  computed: {
    iconColor() {
      const iconTypes: Record<string, string> = {
        'message': 'icon-blue',
        'check-circle': 'icon-green',
        'close-circle': 'icon-red',
        'user': 'icon-purple',
        'info-circle': 'icon-blue'
      };
      return iconTypes[this.icon] || 'icon-blue';
    },
    getBgClass() {
      const bgTypes: Record<string, string> = {
        'message': 'card-blue',
        'check-circle': 'card-green',
        'close-circle': 'card-red',
        'user': 'card-purple',
        'info-circle': 'card-blue'
      };
      return bgTypes[this.icon] || 'card-blue';
    }
  }
});
</script>

<style scoped>
.stat-card {
  background: var(--card-bg);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border: 1px solid var(--border-color);
  border-radius: 16px;
  padding: 20px;
  display: flex;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2),
              0 0 0 1px var(--border-color);
  transition: all 0.3s ease;
  min-width: 200px;
  height: 100%;
  position: relative;
  overflow: hidden;
}

.tech-stat-card::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  border-radius: 16px;
  box-shadow: inset 0 0 0 1px rgba(255, 255, 255, 0.05);
  pointer-events: none;
}

.tech-stat-card::before {
  content: '';
  position: absolute;
  top: -1px;
  left: -1px;
  right: -1px;
  height: 2px;
  opacity: 0.8;
  border-radius: 16px 16px 0 0;
}

.card-blue::before {
  background: linear-gradient(90deg, var(--primary-color), transparent 80%);
}

.card-green::before {
  background: linear-gradient(90deg, var(--success-color), transparent 80%);
}

.card-red::before {
  background: linear-gradient(90deg, var(--error-color), transparent 80%);
}

.card-purple::before {
  background: linear-gradient(90deg, #722ed1, transparent 80%);
}

.stat-card:hover {
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.25),
              0 0 0 1px var(--border-color),
              0 0 10px var(--glow-color);
  transform: translateY(-2px);
}

.card-icon {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 16px;
  font-size: 24px;
  flex-shrink: 0;
  position: relative;
  z-index: 2;
}

.card-glow {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0.03;
  z-index: 1;
  transition: opacity 0.3s ease;
}

.card-line {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 40%;
  height: 1px;
  transition: width 0.3s ease;
}

.stat-card:hover .card-glow {
  opacity: 0.06;
}

.stat-card:hover .card-line {
  width: 70%;
}

.card-blue .card-glow {
  background: radial-gradient(circle at 30% 50%, rgba(59, 130, 246, 0.5), transparent 70%);
}

.card-blue .card-line {
  background: linear-gradient(90deg, rgba(59, 130, 246, 0.5), transparent);
}

.card-green .card-glow {
  background: radial-gradient(circle at 30% 50%, rgba(16, 185, 129, 0.5), transparent 70%);
}

.card-green .card-line {
  background: linear-gradient(90deg, rgba(16, 185, 129, 0.5), transparent);
}

.card-red .card-glow {
  background: radial-gradient(circle at 30% 50%, rgba(239, 68, 68, 0.5), transparent 70%);
}

.card-red .card-line {
  background: linear-gradient(90deg, rgba(239, 68, 68, 0.5), transparent);
}

.card-purple .card-glow {
  background: radial-gradient(circle at 30% 50%, rgba(114, 46, 209, 0.5), transparent 70%);
}

.card-purple .card-line {
  background: linear-gradient(90deg, rgba(114, 46, 209, 0.5), transparent);
}

.icon-blue {
  background-color: rgba(24, 144, 255, 0.15);
  color: #1890ff;
  box-shadow: 0 0 15px rgba(24, 144, 255, 0.2);
}

.icon-green {
  background-color: rgba(82, 196, 26, 0.15);
  color: #52c41a;
  box-shadow: 0 0 15px rgba(82, 196, 26, 0.2);
}

.icon-red {
  background-color: rgba(255, 77, 79, 0.15);
  color: #ff4d4f;
  box-shadow: 0 0 15px rgba(255, 77, 79, 0.2);
}

.icon-purple {
  background-color: rgba(114, 46, 209, 0.15);
  color: #722ed1;
  box-shadow: 0 0 15px rgba(114, 46, 209, 0.2);
}

.card-content {
  flex: 1;
  min-width: 0; /* 防止内容溢出 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  position: relative;
  z-index: 2;
}

.card-title {
  font-size: 14px;
  color: rgba(255, 255, 255, 0.7);
  margin: 0 0 8px 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  letter-spacing: 0.5px;
}

.card-value {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 6px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  background: linear-gradient(90deg, #fff, rgba(255, 255, 255, 0.8));
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  letter-spacing: 0.5px;
}

.card-trend {
  font-size: 12px;
  display: flex;
  align-items: center;
  gap: 4px;
}

.trend-up {
  color: #52c41a;
  text-shadow: 0 0 8px rgba(82, 196, 26, 0.3);
}

.trend-down {
  color: #ff4d4f;
  text-shadow: 0 0 8px rgba(255, 77, 79, 0.3);
}

/* 响应式样式 */
@media (max-width: 1200px) {
  .stat-card {
    padding: 15px;
  }

  .card-icon {
    width: 40px;
    height: 40px;
    font-size: 20px;
    margin-right: 12px;
  }

  .card-value {
    font-size: 20px;
  }
}

@media (max-width: 768px) {
  .stat-card {
    padding: 12px;
  }

  .card-icon {
    width: 36px;
    height: 36px;
    font-size: 18px;
    margin-right: 10px;
  }

  .card-title {
    font-size: 12px;
    margin-bottom: 6px;
  }

  .card-value {
    font-size: 18px;
    margin-bottom: 4px;
  }

  .card-trend {
    font-size: 11px;
  }
}

@media (max-width: 576px) {
  .card-icon {
    width: 32px;
    height: 32px;
    font-size: 16px;
    margin-right: 8px;
  }

  .card-value {
    font-size: 16px;
  }
}
</style>
