export default {
    install(Vue) {
        Vue.config.globalProperties.getValue = function getValue(src, def) {
            if (src) return src;
            return def;
        };
        Vue.config.globalProperties.getNumber = function getValue(src, def, min) {
            const num = Number(src);
            if (isNaN(num)) return def;
            if (!min || isNaN(min)) return num;
            return num >= min ? num : min;
        };
    }
}