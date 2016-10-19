jQuery.validatePlugin = {
    elementIds: [],
    bind: function (elementIds, isRemove) {
        if (isRemove) {
            this.elementIds = [];
        }
        this.elementIds = elementIds;
        for (var id in elementIds) {
            this.addBlur(elementIds[id]);
            this.removeBorder(elementIds[id]);
        }
    },
    validate: function () {
        for (var id in this.elementIds) {
            if (!this.singleValidate(this.elementIds[id])) {
                return false;
                break;
            }
        }
        return true;
    },
    singleValidate: function (id) {
        var value = $("#" + id).val();
        if (this.isNull(value)) {
            this.addBorder(id);
            return false;
        }
        this.removeBorder(id);
        return true;
    },
    clear: function (elementIds) {
        for (var id in elementIds) {
            $("#" + elementIds[id]).val("");
        }
    },
    rest: function (values) {
        for (var key in values) {
            var value = values[key];
            if (null == value) {
                value = "";
            }
            $("#" + key).val(value);
        }
    },
    isNull: function (value) {
        if (null == value || "" == this.trim(value) || '' == this.trim(value)) {
            return true;
        }
        return false;
    },
    trim: function (value) {
        return value.replace(/(^\s*)|(\s*$)/g, "");
    },
    addBlur: function (id) {
        var that = this;
        $("#" + id).blur(function () {
            if (that.isNull($("#" + id).val())) {
                that.addBorder(id);
            } else {
                that.removeBorder(id);
            }
        });
    },
    addBorder: function (id) {
        $("#" + id).css("border", "1px solid red");
    },
    removeBorder: function (id) {
        $("#" + id).css("border", "");
    },

    //check number
    isNumber: function (value) {
        var s = value;
        var gz = /^[+-]?[0-9.,，;]*$/;
        return gz.test(s);
    },

    isImage: function (id) {
        var value = $("#" + id).val();
        var type = value.substring(value.lastIndexOf(".") + 1);
        if (type == "jpg" || type == "gif" || type == "JPG" || type == "GIF" || type == "PNG" || type == "png") {
            return true;
        } else {
            alert("图片格式不对");
            return false;
        }
    },
    
    isVideo: function (id) {
    	//TODO 等确定视频格式修改
        var value = $("#" + id).val();
        var type = value.substring(value.lastIndexOf(".") + 1);
        if (type == "mp4" || type == "MP4" || type == "3gp" || type == "3GP") {
            return true;
        } else {
            alert("视频格式不对");
            return false;
        }
    },

    //check mobile
    isMobile: function (value) {
        return (/^(?:13\d|15[0-9]|18[0-9]|14[0-9])-?\d{5}(\d{3}|\*{3})$/.test(value));
    },

    //trim left blank
    ltrim: function (value) {
        return s.replace(/^\s*/, "");
    },

    //trim right blank
    rtrim: function (value) {
        return s.replace(/\s*$/, "");
    }
};




