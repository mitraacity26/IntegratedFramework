function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }
  var config = {

    baseURL: 'https://reqres.in'
  }
  if (env == 'dev') {

    config.baseURL = 'https://reqres.in'

  } else if (env == 'qa') {

    config.baseURL = 'https://reqres.in'
  }
  return config;
}