import axios from 'axios';
const baseURL = "http://127.0.0.1:8080/api/"


const customHeaders = {
  'Authorization': 'Bearer '+localStorage.getItem("token"),
};

const config = {
  headers: customHeaders,
};


async function Get(path) {
  try {
      const response = await axios.get(baseURL + path, config);
      return response.data;
  } catch (error) {
      console.error(error);
      throw error;
  }
}


function Login(path, body) {
  return axios.post(baseURL + path, body)
    .then(response => {
      console.log(response.data);
      if(response.status === 200){
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("id", response.data.id);
        localStorage.setItem("username", response.data.username);

      }
      return response.status === 200;
    })
    .catch(error => {
      console.error(error);
      throw error;
    });
}

async function GetMessages(path){
  try {
    const response = await axios.get(baseURL + path, config);
    return response.data;
  } catch (error) {
      console.error(error);
      throw error;
  }
}

export default { Get, Login };


