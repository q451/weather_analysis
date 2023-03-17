<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-pie-chart"></i> 天气分析图表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="schart-box" 
                        v-loading="loading1"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading">
                <div class="content-title">柱状图</div>
                <schart class="schart" canvasId="bar" :options="options1"></schart>
            </div>
            <div class="schart-box"
                        v-loading="loading1"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading">
                <div class="content-title">折线图</div>
                <schart class="schart" canvasId="line" :options="options2"></schart>
            </div>
            <div class="schart-box">
                <div class="content-title">饼状图
                    <el-select v-model="value" filterable  placeholder="请选择展示的年份" >
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                        @click.native="selectYear1111(item.value)">
                        </el-option>
                    </el-select>
                </div>
                <schart class="schart" canvasId="pie" :options="options3"  
                        v-loading="loading2"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading"></schart>
            </div>
            <div class="schart-box">
                <div class="content-title">环形图
                    <el-select v-model="value1" filterable  placeholder="请选择展示的年份">
                        <el-option
                        v-for="item in optionsSan"
                        :key="item.value1"
                        :label="item.label"
                        :value="item.value1"
                        @click.native="selectYear(item.value1)">
                        </el-option>
                    </el-select>
                </div>
                <schart class="schart" canvasId="ring" :options="options4" key=""
                        v-loading="loading2"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading"></schart>
            </div>
        </div>
    </div>
</template>

<script>
import Schart from 'vue-schart';
import { getMonthAgrTemperature, everyYearAnalysis} from '@/api/index'
export default {
    name: 'basecharts',
    components: {
        Schart
    },
    data() {
        return {
            options: [
                {
                    value: '2019',
                    label: '2019'
                }, 
                {
                    value: '2020',
                    label: '2020'
                }, 
                {
                    value: '2021',
                    label: '2021'
                },
                {
                    value: '2022',
                    label: '2022'
                }, 
                {
                    value: '2023',
                    label: '2023'
                }
            ],
            optionsSan: [
                {
                    value1: '2019',
                    label: '2019'
                }, 
                {
                    value1: '2020',
                    label: '2020'
                }, 
                {
                    value1: '2021',
                    label: '2021'
                },
                {
                    value1: '2022',
                    label: '2022'
                }, 
                {
                    value1: '2023',
                    label: '2023'
                }
            ],

            loading1: true,
            loading2: true,

            value: '2022',
            value1: '2022',
            options1: {
                type: 'bar',
                title: {
                    text: '2019到2023年平均气温柱状图'
                },
                bgColor: '#fbfbfb',
                labels: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一', '十二'],
                datasets: [
                    {
                        label: '2019年平均气温值',
                        data: []
                    },
                    {
                        label: '2020年平均气温值',
                        data: []
                    },
                    {
                        label: '2021年平均气温值',
                        data: []
                    },
                    {
                        label: '2022年平均气温值',
                        data: []
                    },
                    {
                        label: '2023年平均气温值',
                        data: []
                    }
                ]
            },
            options2: {
                type: 'line',
                title: {
                    text: '2019到2023年平均气温趋势图'
                },
                bgColor: '#fbfbfb',
                labels: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一', '十二'],
                datasets: [
                    {
                        label: '2019年平均气温值',
                        data: []
                    },
                    {
                        label: '2020年平均气温值',
                        data: []
                    },
                    {
                        label: '2021年平均气温值',
                        data: []
                    },
                    {
                        label: '2022年平均气温值',
                        data: []
                    },
                    {
                        label: '2023年平均气温值',
                        data: []
                    }
                ]
            },
            options3: {
                type: 'pie',
                title: {
                    text: ''
                },
                legend: {
                    position: 'left'
                },
                bgColor: '#fbfbfb',
                labels: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一', '十二'],
                datasets: [
                    {
                        data: []
                    }
                ]
            },
            options4: {
                type: 'ring',
                title: {
                    text: ''
                },
                showValue: true,
                legend: {
                    position: 'bottom',
                    bottom: 40
                },
                bgColor: '#fbfbfb',
                labels: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一', '十二'],
                datasets: [
                    {
                        data: []
                    }
                ]
            }
        };
    },
    mounted() {
        this.Analysis(),
        this.getMonthAgrTemperature()
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        Analysis() {
            everyYearAnalysis().then(response => {
                console.log(response);
                console.log(response.data.code);
                if(response.status == 200){
                }
            }).catch(error=>{
                console.log(error)
            });
        },
        getMonthAgrTemperature() {
            getMonthAgrTemperature().then(response => {
                var infoLives = response.data.data;

                this.options3.datasets[0].data = infoLives[this.value];
                this.options3.title.text = this.value + '年平均气温饼状图';

                this.options4.datasets[0].data = infoLives[this.value1];
                this.options4.title.text = this.value1 + '年平均气温环形三等分';
                console.log(infoLives[this.value]);

                this.options1.datasets[0].data = infoLives["2019"];
                this.options1.datasets[1].data = infoLives["2020"];
                this.options1.datasets[2].data = infoLives["2021"];
                this.options1.datasets[3].data = infoLives["2022"];
                this.options1.datasets[4].data = infoLives["2023"];

                this.options2.datasets[0].data = infoLives["2019"];
                this.options2.datasets[1].data = infoLives["2020"];
                this.options2.datasets[2].data = infoLives["2021"];
                this.options2.datasets[3].data = infoLives["2022"];
                this.options2.datasets[4].data = infoLives["2023"];

                if(response.status == 200){
                    this.loading1 = false;
                    this.loading2 = false;
                }
            }).catch(error=>{
                console.log(error)
            });
        },
        selectYear(selectValue) {
            this.loading2 = true;
            getMonthAgrTemperature().then(response => {
                var infoLives = response.data.data;
                this.options4.datasets[0].data = infoLives[selectValue];
                this.options4.title.text = selectValue + '年平均气温环形三等分';
                console.log(infoLives[selectValue]);
                if(response.status == 200){
                    this.loading2 = false
                }
            }).catch(error=>{
                console.log(error)
            });
        },
        selectYear1111(selectValue) {
            this.loading2 = true;
            getMonthAgrTemperature().then(response => {
                var infoLives = response.data.data;
                this.options3.datasets[0].data = infoLives[selectValue];
                this.options3.title.text = selectValue + '年平均气温饼状图';
                console.log(infoLives[selectValue]);
                if(response.status == 200){
                    this.loading2 = false
                }
            }).catch(error=>{
                console.log(error)
            });
        },
    }
};
</script>

<style scoped>

.schart-box {
    display: inline-block;
    margin: 20px;
}
.schart {
    width: 1300px;
    height: 400px;
}
.content-title {
    clear: both;
    font-weight: 400;
    line-height: 50px;
    margin: 10px 0;
    font-size: 22px;
    color: #1f2f3d;
}
</style>