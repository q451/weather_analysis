import request from '../utils/request';

import { ToFormData } from '@/utils/toformdata'

/**
 * 单个参数使用 transformRequest: [ToFormData]
 * 传对象不使用 transformRequest: [ToFormData]
 * 
 * @param {*} data 
 * @returns 
 */

export function login(data) {
    return request({
        url: '/user/login',
        method: 'post',
        data,
        transformRequest: [ToFormData]
    })
}

export function getWeather() {
    return request({
        url: '/weather/weatherList',
        method: 'get'
    })
}

export function updateWeather() {
    return request({
        url: '/weather/updateWeather',
        method: 'get'
    })
}

export function everyYearAnalysis() {
    return request({
        url: '/weather/everyYearAnalysis',
        method: 'get'
    })
}

export function getThisDayList() {
    return request({
        url: '/weather/getThisDayList',
        method: 'get'
    })
}

export function getThisDayLatest() {
    return request({
        url: '/weather/getThisDayLatest',
        method: 'get'
    })
}

export function getMonthAgrTemperature() {
    return request({
        url: '/weather/getMonthAgrTemperature',
        method: 'get'
    })
}

export function getStatistics() {
    return request({
        url: '/weather/getStatistics',
        method: 'get'
    })
}

export function getXData() {
    return request({
        url: '/weather/getXData',
        method: 'get'
    })
}



