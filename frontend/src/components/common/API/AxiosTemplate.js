import Axios from 'axios';

export default Axios.create({
    headers: {'X-Requested-With': 'XMLHttpRequest'}
})