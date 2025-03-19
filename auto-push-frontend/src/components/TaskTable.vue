<template>
  <div class="task-table">
    <a-table
      :columns="columns"
      :data-source="tasks"
      :pagination="{ pageSize: 5, showSizeChanger: false }"
      :scroll="{ x: '100%' }"
      size="middle"
      :loading="loading"
      :rowClassName="() => 'tech-table-row'"
      class="tech-table"
    >
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'status'">
          <a-tag :color="record.status === '成功' ? 'success' : 'error'" class="tech-tag">
            <span class="tag-dot"></span>
            {{ record.status }}
          </a-tag>
        </template>

        <template v-if="column.key === 'operation'">
          <div class="operation-buttons">
            <a-button type="link" size="small" class="tech-btn edit-btn">
              <template #icon><a-icon type="edit" /></template>
              <span class="button-text">编辑</span>
              <span class="btn-glow"></span>
            </a-button>
            <a-button type="link" size="small" class="tech-btn log-btn">
              <template #icon><a-icon type="file-text" /></template>
              <span class="button-text">日志</span>
              <span class="btn-glow"></span>
            </a-button>
            <a-button type="link" size="small" class="tech-btn delete-btn" @click="handleDelete(record)">
              <template #icon><a-icon type="delete" /></template>
              <span class="button-text">删除</span>
              <span class="btn-glow"></span>
            </a-button>
          </div>
        </template>
      </template>
    </a-table>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, onMounted } from 'vue';
import { message } from 'ant-design-vue';

interface Task {
  key: string;
  name: string;
  channel: string;
  target: string;
  createTime: string;
  status: string;
}

export default defineComponent({
  name: 'TaskTable',
  setup() {
    const loading = ref(false);
    const windowWidth = ref(window.innerWidth);

    const updateWindowWidth = () => {
      windowWidth.value = window.innerWidth;
    };

    // 监听窗口大小变化
    onMounted(() => {
      window.addEventListener('resize', updateWindowWidth);
    });

    // 根据屏幕宽度计算需要显示的列
    const columns = computed(() => {
      const isSmallScreen = windowWidth.value < 768;

      const baseColumns = [
        {
          title: '排序',
          dataIndex: 'key',
          key: 'key',
          width: isSmallScreen ? 60 : 80,
          responsive: ['md'],
          className: 'column-index'
        },
        {
          title: '任务名称',
          dataIndex: 'name',
          key: 'name',
          width: isSmallScreen ? 150 : 180,
          ellipsis: true,
          className: 'column-name'
        },
        {
          title: '发送渠道',
          dataIndex: 'channel',
          key: 'channel',
          width: isSmallScreen ? 100 : 120,
          ellipsis: true,
          responsive: ['sm'],
          className: 'column-channel'
        },
        {
          title: '渠道策略',
          dataIndex: 'target',
          key: 'target',
          width: isSmallScreen ? 100 : 120,
          ellipsis: true,
          responsive: ['lg'],
          className: 'column-strategy'
        },
        {
          title: '创建时间',
          dataIndex: 'createTime',
          key: 'createTime',
          width: isSmallScreen ? 120 : 150,
          responsive: ['md'],
          className: 'column-time'
        },
        {
          title: '状态',
          dataIndex: 'status',
          key: 'status',
          width: 80,
          className: 'column-status'
        },
        {
          title: '操作',
          key: 'operation',
          fixed: isSmallScreen ? false : 'right',
          width: isSmallScreen ? 120 : 200,
          className: 'column-operation'
        },
      ];

      // 小屏幕时只显示最重要的列
      return isSmallScreen
        ? baseColumns.filter(col => !col.responsive || col.responsive.includes('xs'))
        : baseColumns;
    });

    const tasks = ref<Task[]>([
      {
        key: '1',
        name: '系统告警通知',
        channel: '邮箱, 企业微信',
        target: '同时使用',
        createTime: '2024-01-15 14:30',
        status: '成功',
      },
      {
        key: '2',
        name: '每日数据报表',
        channel: '钉钉, 飞书',
        target: '自动容错',
        createTime: '2024-01-15 15:00',
        status: '失败',
      },
      {
        key: '3',
        name: '服务器监控告警',
        channel: 'Webhook',
        target: '同时使用',
        createTime: '2024-01-15 16:20',
        status: '成功',
      },
    ]);

    const handleDelete = (record: Task) => {
      loading.value = true;
      setTimeout(() => {
        tasks.value = tasks.value.filter(item => item.key !== record.key);
        message.success('删除成功');
        loading.value = false;
      }, 500);
    };

    return {
      columns,
      tasks,
      handleDelete,
      loading,
    };
  },
});
</script>

<style scoped>
.task-table {
  width: 100%;
  overflow-x: auto;
}

/* 科技感表格样式 */
:deep(.tech-table) {
  background: transparent;
  border-radius: 12px;
  overflow: hidden;
}

:deep(.tech-table .ant-table) {
  background: transparent;
}

:deep(.tech-table .ant-table-thead > tr > th) {
  background: rgba(59, 130, 246, 0.1);
  border-bottom: 1px solid var(--border-color);
  color: rgba(255, 255, 255, 0.9);
  font-weight: 600;
  padding: 12px 16px;
  transition: background 0.3s;
}

:deep(.tech-table .ant-table-thead > tr > th::before) {
  display: none;
}

:deep(.tech-table .ant-table-tbody > tr > td) {
  border-bottom: 1px solid rgba(59, 130, 246, 0.1);
  padding: 12px 16px;
  transition: all 0.3s ease;
  background: transparent;
}

:deep(.tech-table-row) {
  transition: all 0.3s;
  position: relative;
}

:deep(.tech-table-row:hover > td) {
  background: rgba(59, 130, 246, 0.05) !important;
}

:deep(.tech-table-row:hover) {
  transform: translateY(-1px);
  z-index: 1;
}

:deep(.tech-table .ant-table-tbody > tr.ant-table-row:hover > td) {
  background: rgba(59, 130, 246, 0.05);
}

:deep(.ant-table-tbody > tr.ant-table-row:hover) {
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
}

:deep(.ant-table-pagination.ant-pagination) {
  margin: 16px 0;
}

:deep(.ant-pagination-item) {
  border-color: var(--border-color);
  background: transparent;
  transition: all 0.3s;
}

:deep(.ant-pagination-item a) {
  color: rgba(255, 255, 255, 0.8);
}

:deep(.ant-pagination-item:hover) {
  border-color: var(--primary-color);
}

:deep(.ant-pagination-item:hover a) {
  color: var(--primary-color);
}

:deep(.ant-pagination-item-active) {
  border-color: var(--primary-color);
  background: linear-gradient(90deg, var(--primary-color), var(--primary-hover));
}

:deep(.ant-pagination-item-active a) {
  color: #fff;
}

:deep(.ant-pagination-prev .ant-pagination-item-link),
:deep(.ant-pagination-next .ant-pagination-item-link) {
  background: transparent;
  border-color: var(--border-color);
  color: rgba(255, 255, 255, 0.8);
}

:deep(.ant-pagination-prev:hover .ant-pagination-item-link),
:deep(.ant-pagination-next:hover .ant-pagination-item-link) {
  border-color: var(--primary-color);
  color: var(--primary-color);
}

/* 标签样式 */
:deep(.tech-tag) {
  border: none;
  display: inline-flex;
  align-items: center;
  border-radius: 4px;
  height: 24px;
  padding: 0 8px;
  position: relative;
  overflow: hidden;
}

:deep(.tech-tag::after) {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, rgba(255, 255, 255, 0.1), rgba(255, 255, 255, 0));
  border-radius: 4px;
  pointer-events: none;
}

:deep(.tag-dot) {
  width: 6px;
  height: 6px;
  border-radius: 50%;
  margin-right: 6px;
  display: inline-block;
  background-color: currentColor;
  box-shadow: 0 0 5px currentColor;
  animation: dot-pulse 2s infinite alternate;
}

@keyframes dot-pulse {
  0% { opacity: 0.7; }
  100% { opacity: 1; }
}

.operation-buttons {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.tech-btn {
  position: relative;
  transition: all 0.3s;
  overflow: hidden;
  border-radius: 4px;
  padding: 1px 4px;
}

.tech-btn:hover {
  background: rgba(255, 255, 255, 0.05);
}

.btn-glow {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 0;
  height: 1px;
  transition: width 0.3s ease;
}

.tech-btn:hover .btn-glow {
  width: 100%;
}

.edit-btn {
  color: var(--primary-color) !important;
}

.edit-btn .btn-glow {
  background: linear-gradient(90deg, var(--primary-color), transparent);
}

.log-btn {
  color: var(--success-color) !important;
}

.log-btn .btn-glow {
  background: linear-gradient(90deg, var(--success-color), transparent);
}

.delete-btn {
  color: var(--error-color) !important;
}

.delete-btn .btn-glow {
  background: linear-gradient(90deg, var(--error-color), transparent);
}

@media (max-width: 576px) {
  .button-text {
    display: none;
  }

  .operation-buttons {
    gap: 4px;
  }
}
</style>
