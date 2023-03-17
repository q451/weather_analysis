<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-data-analysis"></i> 天气分析表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div ref="chart" style="width:100%;height:500px"></div> 
        </div>
    </div>
</template>

<script>
import Schart from 'vue-schart';
import { getXData, everyYearAnalysis} from '@/api/index'
export default {
    name: 'baseform',
    components: {
        Schart
    },
    data() {
        return {
            labelsTemp: ''
        };
    },
    mounted() {
        this.Analysis(),
        this.getHengData()
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        Analysis() {
            everyYearAnalysis().then(response => {
                var infoLives = response.data.data;
                const chart = this.$refs.chart
                if (chart) {
                    const myChart = this.$echarts.init(chart)
                    const option = {
                        title: { 
                            left: 'center',
                            text: '2019到2023年气温趋势图' 
                        },
                        tooltip: {
                            trigger: 'axis',
                            position: function (pt) {
                                return [pt[0], '10%'];
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: this.labelsTemp
                        },
                        yAxis: {
                            type: 'value',
                            boundaryGap: [0, '10%']
                        },
                        dataZoom: [
                            {
                                type: 'inside',
                                start: 0,
                                end: 10
                            },
                            {
                                start: 0,
                                end: 10
                            }
                        ],
                        series: [
                           
                            {
                                name: '2019',
                                symbol: 'none',
                                areaStyle: {},
                                data: infoLives["agr2019"],
                                type: 'line',
                                smooth: true
                            },
                            {
                                name: '2020',
                                symbol: 'none',
                                areaStyle: {},
                                data: infoLives["agr2020"],
                                type: 'line',
                                smooth: true
                            },
                            {
                                name: '2021',
                                symbol: 'none',
                                areaStyle: {},
                                data: infoLives["agr2021"],
                                type: 'line',
                                smooth: true
                            },
                            {
                                name: '2022',
                                symbol: 'none',
                                areaStyle: {},
                                data: infoLives["agr2022"],
                                type: 'line',
                                smooth: true
                            },
                            {
                                name: '2023',
                                symbol: 'none',
                                areaStyle: {},
                                data: infoLives["agr2023"],
                                type: 'line',
                                smooth: true
                            }
                        ]
                    }
                    myChart.setOption(option)
                }
            }).catch(error=>{
                console.log(error)
            });
        },
        getHengData() {
            getXData().then(response => {
               var labels = response.data.data;
               this.labelsTemp = labels;
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
    width: 100%;
    height: 600px;
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