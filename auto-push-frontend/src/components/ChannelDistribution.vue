<template>
  <div class="channel-distribution-chart" ref="chartRef"></div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, onUnmounted } from 'vue';
import * as echarts from 'echarts';

export default defineComponent({
  name: 'ChannelDistribution',
  setup() {
    const chartRef = ref<HTMLElement | null>(null);
    let chart: echarts.ECharts | null = null;

    const resizeChart = () => {
      chart?.resize();
    };

    const initChart = () => {
      if (!chartRef.value) return;

      // 销毁之前的图表实例（如果存在）
      if (chart) {
        chart.dispose();
      }

      // 定义渠道数据
      const data = [
        { name: '邮箱', value: 35 },
        { name: '企业微信', value: 25 },
        { name: 'HTTP', value: 20 },
        { name: '飞书', value: 15 },
        { name: 'Webhook', value: 5 }
      ];

      // 颜色映射
      const colors = ['#5B8FF9', '#5AD8A6', '#F6BD16', '#FF6B3B', '#6DC8EC'];

      // 创建图表实例
      chart = echarts.init(chartRef.value, null, { renderer: 'canvas' });

      // 根据容器宽度调整图表布局
      const containerWidth = chartRef.value.clientWidth;
      const isSmallScreen = containerWidth < 400;

      const option = {
        backgroundColor: 'transparent',
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)',
          backgroundColor: '#1a2030',
          borderWidth: 0,
          textStyle: {
            color: '#fff'
          },
          confine: true
        },
        legend: {
          orient: isSmallScreen ? 'horizontal' : 'vertical',
          right: isSmallScreen ? 'center' : 10,
          bottom: isSmallScreen ? 0 : undefined,
          top: isSmallScreen ? undefined : 'center',
          textStyle: {
            color: '#8c8c8c',
            fontSize: 12
          },
          itemWidth: 15,
          itemHeight: 10,
          itemGap: 10,
          data: data.map(item => item.name)
        },
        series: [
          {
            name: '渠道分布',
            type: 'pie',
            radius: isSmallScreen ? ['40%', '60%'] : ['50%', '70%'],
            center: isSmallScreen ? ['50%', '40%'] : ['50%', '50%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 4,
              borderColor: '#1a2030',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: isSmallScreen ? '12' : '14',
                fontWeight: 'bold',
                color: '#fff'
              }
            },
            labelLine: {
              show: false
            },
            data: data,
            color: colors
          }
        ],
        responsive: true,
        maintainAspectRatio: false
      };

      chart.setOption(option);

      // 添加响应式调整
      window.addEventListener('resize', resizeChart);
    };

    onMounted(() => {
      initChart();

      // 使用ResizeObserver监听容器大小变化
      if (window.ResizeObserver) {
        const observer = new ResizeObserver(() => {
          initChart(); // 重新初始化图表以适应新的容器大小
        });
        if (chartRef.value) {
          observer.observe(chartRef.value);
        }
      }
    });

    onUnmounted(() => {
      // 组件销毁时移除事件监听和销毁图表
      window.removeEventListener('resize', resizeChart);
      chart?.dispose();
    });

    return {
      chartRef
    };
  }
});
</script>

<style scoped>
.channel-distribution-chart {
  width: 100%;
  height: 300px;
  min-height: 200px;
}

@media (max-width: 576px) {
  .channel-distribution-chart {
    height: 280px;
  }
}
</style>
