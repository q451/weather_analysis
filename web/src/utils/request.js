import axios from 'axios';

const service = axios.create({
    // process.env.NODE_ENV === 'development' 来判断是否开发环境
    // easy-mock服务挂了，暂时不使用了
    // baseURL: 'http://120.48.48.165:9001',
    baseURL: 'http://localhost:9001',
    timeout: 10000
});

// service.interceptors.request.use(
//     config => {
//         return config;
//     },
//     error => {
//         console.log(error);
//         return Promise.reject();
//     }
// );

// service.interceptors.response.use(
//     response => {
//         if (response.status === 200) {
//             return response.data;
//         } else {
//             Promise.reject();
//         }
//     },
//     error => {
//         console.log(error);
//         return Promise.reject();
//     }
// );
// service.interceptors.request.use(
//     config => {
//       //设置请求头信息
//       let token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ5YW5nWGlhb1BpbmciLCJwYXNzd29yZCI6IjE5MzAxMTA1IiwiZXhwIjoxNzEwNTU4MzE4LCJ1c2VyaWQiOiIxOTMwMTEwNSIsInN0YXR1cyI6MX0.eMSC6Ix9AUV9Kpn8V3olhC17_rAT7rw2rGZXvA60o2I';
//       const headersOption = {
//         token
//       };
//       config.headers = Object.assign(config.headers, headersOption);
//       return config;
//     },
//     err => {
//       console.log(err);
//     },
// );

export default service;
