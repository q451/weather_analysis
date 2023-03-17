<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 历史天气
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    type="success"
                    icon="el-icon-update"
                    @click="updateBeijingWeather()"
                >天气更新</el-button>

                <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                    @click="delAllSelection"
                >批量删除</el-button>

                <el-select v-model="query.date" placeholder="年份" class="handle-select mr10">
                    <el-option key="1" label="2019" value="2019"></el-option>
                    <el-option key="2" label="2020" value="2020"></el-option>
                    <el-option key="3" label="2021" value="2021"></el-option>
                    <el-option key="4" label="2022" value="2022"></el-option>
                    <el-option key="5" label="2023" value="2023"></el-option>
                </el-select>
                <el-input v-model="query.weather" placeholder="天气情况" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <el-table
                v-loading="loading"
                element-loading-text="拼命加载中"
                element-loading-spinner="el-icon-loading"
                height="440"
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>

                <el-table-column prop="date" label="日期"></el-table-column>

                <el-table-column prop="week" label="星期"></el-table-column>

                <el-table-column prop="max" label="最高气温"></el-table-column>

                <el-table-column prop="min" label="最低气温"></el-table-column>

                <el-table-column prop="weather" label="天气"></el-table-column>

                <el-table-column prop="wind" label="风向强度"></el-table-column>

            </el-table>

            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import { getWeather, updateWeather} from '@/api/index'
export default {
    name: 'basetable',
    data() {
        return {
            query: {
                date: '',
                weather: '',
                pageIndex: 1,
                pageSize: 10
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            pageTotal: 0,
            form: {},
            idx: -1,
            id: -1,
            loading: true
        };
    },
    created() {
        this.getData();
    },
    mounted() {
        this.Weather()
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        Weather() {
            getWeather(this.query).then(response => {
                console.log(response);
                this.tableData = response.data.data.data;
                console.log(response.data.data.code);
                if(response.status == 200){
                    this.loading = false
                }
                this.pageTotal = response.data.data.count || 50;
            }).catch(error=>{
                console.log(error)
            });
        },
        updateBeijingWeather(){
            this.loading = true
            updateWeather().then(response => {
                console.log(response);
                if(response.status == 200){
                    console.log(11111111111111);
                    this.loading = false
                }
            }).catch(error=>{
                console.log(error)
            });
        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    this.$message.success('删除成功');
                    this.tableData.splice(index, 1);
                })
                .catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const length = this.multipleSelection.length;
            let str = '';
            this.delList = this.delList.concat(this.multipleSelection);
            for (let i = 0; i < length; i++) {
                str += this.multipleSelection[i].name + ' ';
            }
            this.$message.error(`删除了${str}`);
            this.multipleSelection = [];
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = row;
            this.editVisible = true;
        },
        // 保存编辑
        saveEdit() {
            this.editVisible = false;
            this.$message.success(`修改第 ${this.idx + 1} 行成功`);
            this.$set(this.tableData, this.idx, this.form);
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
