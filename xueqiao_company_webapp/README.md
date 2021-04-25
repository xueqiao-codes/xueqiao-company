# xueqiao_company_webapp

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your tests
```
npm run test
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


plugin:
### js-cookie
npm install js-cookie --save
import Cookies from "js-cookie"


### some browser do not support es6, like ie10:
npm install --save babel-polyfill

add in main.js:
import 'babel-polyfill'