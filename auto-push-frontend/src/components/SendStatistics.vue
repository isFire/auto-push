<template>
  <div class="send-statistics-chart" ref="chartRef"></div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, onUnmounted, watch } from 'vue';
import * as echarts from 'echarts';

export default defineComponent({
  name: 'SendStatistics',
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

      chart = echarts.init(chartRef.value, null, { renderer: 'canvas' });

      const option = {
        backgroundColor: 'transparent',
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          top: '10%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
          axisLine: {
            lineStyle: {
              color: '#303a4d'
            }
          },
          axisLabel: {
            color: '#8c8c8c',
            fontSize: 12,
            rotate: window.innerWidth < 500 ? 30 : 0
          }
        },
        yAxis: {
          type: 'value',
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          splitLine: {
            lineStyle: {
              color: '#303a4d',
              type: 'dashed'
            }
          },
          axisLabel: {
            color: '#8c8c8c',
            fontSize: 12
          }
        },
        tooltip: {
          trigger: 'axis',
          backgroundColor: '#1a2030',
          borderWidth: 0,
          textStyle: {
            color: '#fff'
          },
          confine: true
        },
        legend: {
          data: ['成功', '失败'],
          textStyle: {
            color: '#8c8c8c',
            fontSize: 12
          },
          right: 0,
          top: 0,
          itemWidth: 15,
          itemHeight: 10,
          itemGap: 10
        },
        series: [
          {
            name: '成功',
            type: 'line',
            stack: 'Total',
            smooth: true,
            data: [150, 232, 226, 218, 145, 148, 250],
            areaStyle: {
              opacity: 0.2
            },
            itemStyle: {
              color: '#52c41a'
            },
            lineStyle: {
              color: '#52c41a',
              width: 2
            },
            symbolSize: 5
          },
          {
            name: '失败',
            type: 'line',
            stack: 'Total',
            smooth: true,
            data: [20, 28, 25, 24, 22, 23, 30],
            areaStyle: {
              opacity: 0.2
            },
            itemStyle: {
              color: '#ff4d4f'
            },
            lineStyle: {
              color: '#ff4d4f',
              width: 2
            },
            symbolSize: 5
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
          resizeChart();
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
.send-statistics-chart {
  width: 100%;
  height: 300px;
  min-height: 200px;
}

@media (max-width: 576px) {
  .send-statistics-chart {
    height: 250px;
  }
}
</style>
