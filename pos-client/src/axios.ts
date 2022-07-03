import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:30080",
});

export { http };
