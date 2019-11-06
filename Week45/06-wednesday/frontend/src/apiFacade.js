const URL = 'http://localhost:8080/securitystarter';
function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({status: res.status, fullError: res.json()});
  }
  return res.json();
}

class ApiFacade {
  setToken = token => {
    localStorage.setItem('jwtToken', token);
  };
  getToken = () => {
    return localStorage.getItem('jwtToken');
  };
  loggedIn = () => {
    const loggedIn = this.getToken() != null;
    return loggedIn;
  };
  logout = () => {
    localStorage.removeItem('jwtToken');
  };

  login = (user, pass) => {
    const options = this.makeOptions('POST', true, {
      username: user,
      password: pass
    });
    return fetch(URL + '/api/login', options)
      .then(handleHttpErrors)
      .then(res => {
        console.log('resHere', res);
        if (res.token) this.setToken(res.token);
        else throw new Error(res);
      });
    //s.catch(error => console.log('hereError', error)); //I would have added error handling here, but it was not wise, as it let users "log in" without a token. Out of scope
  };

  fetchData = () => {
    const options = this.makeOptions('GET', true); //True add's the token
    return fetch(URL + '/api/info/user', options).then(
      handleHttpErrors
    ); /*This code is wrong and only handles USERS, not admins */
  };

  makeOptions(method, addToken, body) {
    var opts = {
      method: method,
      headers: {
        'Content-type': 'application/json',
        Accept: 'application/json'
      }
    };
    if (addToken && this.loggedIn()) {
      opts.headers['x-access-token'] = this.getToken();
    }
    if (body) {
      opts.body = JSON.stringify(body);
    }
    return opts;
  }
}
const facade = new ApiFacade();
export default facade;
