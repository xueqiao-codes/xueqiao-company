//
// Autogenerated by Thrift Compiler (0.9.1)
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//


WorkingOrderType = {
'ASSET_ACCOUNT' : 0
};
WorkingOrderState = {
'CREATE' : 0,
'IN_PROGRESS' : 1,
'DONE' : 2
};
AssetAccount = function(args) {
  this.accountName = null;
  this.password = null;
  this.nickName = null;
  this.authorizationCode = null;
  this.brokerId = null;
  this.brokerAccessId = null;
  this.extraInfo = null;
  if (args) {
    if (args.accountName !== undefined) {
      this.accountName = args.accountName;
    }
    if (args.password !== undefined) {
      this.password = args.password;
    }
    if (args.nickName !== undefined) {
      this.nickName = args.nickName;
    }
    if (args.authorizationCode !== undefined) {
      this.authorizationCode = args.authorizationCode;
    }
    if (args.brokerId !== undefined) {
      this.brokerId = args.brokerId;
    }
    if (args.brokerAccessId !== undefined) {
      this.brokerAccessId = args.brokerAccessId;
    }
    if (args.extraInfo !== undefined) {
      this.extraInfo = args.extraInfo;
    }
  }
};
AssetAccount.prototype = {};
AssetAccount.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRING) {
        this.accountName = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.password = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.STRING) {
        this.nickName = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.STRING) {
        this.authorizationCode = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.I64) {
        this.brokerId = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 6:
      if (ftype == Thrift.Type.I64) {
        this.brokerAccessId = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 7:
      if (ftype == Thrift.Type.MAP) {
        var _size0 = 0;
        var _rtmp34;
        this.extraInfo = {};
        var _ktype1 = 0;
        var _vtype2 = 0;
        _rtmp34 = input.readMapBegin();
        _ktype1 = _rtmp34.ktype;
        _vtype2 = _rtmp34.vtype;
        _size0 = _rtmp34.size;
        for (var _i5 = 0; _i5 < _size0; ++_i5)
        {
          if (_i5 > 0 ) {
            if (input.rstack.length > input.rpos[input.rpos.length -1] + 1) {
              input.rstack.pop();
            }
          }
          var key6 = null;
          var val7 = null;
          key6 = input.readString().value;
          val7 = input.readString().value;
          this.extraInfo[key6] = val7;
        }
        input.readMapEnd();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

AssetAccount.prototype.write = function(output) {
  output.writeStructBegin('AssetAccount');
  if (this.accountName !== null && this.accountName !== undefined) {
    output.writeFieldBegin('accountName', Thrift.Type.STRING, 1);
    output.writeString(this.accountName);
    output.writeFieldEnd();
  }
  if (this.password !== null && this.password !== undefined) {
    output.writeFieldBegin('password', Thrift.Type.STRING, 2);
    output.writeString(this.password);
    output.writeFieldEnd();
  }
  if (this.nickName !== null && this.nickName !== undefined) {
    output.writeFieldBegin('nickName', Thrift.Type.STRING, 3);
    output.writeString(this.nickName);
    output.writeFieldEnd();
  }
  if (this.authorizationCode !== null && this.authorizationCode !== undefined) {
    output.writeFieldBegin('authorizationCode', Thrift.Type.STRING, 4);
    output.writeString(this.authorizationCode);
    output.writeFieldEnd();
  }
  if (this.brokerId !== null && this.brokerId !== undefined) {
    output.writeFieldBegin('brokerId', Thrift.Type.I64, 5);
    output.writeI64(this.brokerId);
    output.writeFieldEnd();
  }
  if (this.brokerAccessId !== null && this.brokerAccessId !== undefined) {
    output.writeFieldBegin('brokerAccessId', Thrift.Type.I64, 6);
    output.writeI64(this.brokerAccessId);
    output.writeFieldEnd();
  }
  if (this.extraInfo !== null && this.extraInfo !== undefined) {
    output.writeFieldBegin('extraInfo', Thrift.Type.MAP, 7);
    output.writeMapBegin(Thrift.Type.STRING, Thrift.Type.STRING, Thrift.objectLength(this.extraInfo));
    for (var kiter8 in this.extraInfo)
    {
      if (this.extraInfo.hasOwnProperty(kiter8))
      {
        var viter9 = this.extraInfo[kiter8];
        output.writeString(kiter8);
        output.writeString(viter9);
      }
    }
    output.writeMapEnd();
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

BaseWorkingOrder = function(args) {
  this.orderId = null;
  this.companyId = null;
  this.companyUserId = null;
  this.workingOrderType = null;
  this.state = null;
  this.createTimestamp = null;
  this.lastModifyTimestamp = null;
  this.operateUser = null;
  if (args) {
    if (args.orderId !== undefined) {
      this.orderId = args.orderId;
    }
    if (args.companyId !== undefined) {
      this.companyId = args.companyId;
    }
    if (args.companyUserId !== undefined) {
      this.companyUserId = args.companyUserId;
    }
    if (args.workingOrderType !== undefined) {
      this.workingOrderType = args.workingOrderType;
    }
    if (args.state !== undefined) {
      this.state = args.state;
    }
    if (args.createTimestamp !== undefined) {
      this.createTimestamp = args.createTimestamp;
    }
    if (args.lastModifyTimestamp !== undefined) {
      this.lastModifyTimestamp = args.lastModifyTimestamp;
    }
    if (args.operateUser !== undefined) {
      this.operateUser = args.operateUser;
    }
  }
};
BaseWorkingOrder.prototype = {};
BaseWorkingOrder.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I64) {
        this.orderId = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.I64) {
        this.companyId = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.I64) {
        this.companyUserId = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.I32) {
        this.workingOrderType = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.I32) {
        this.state = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 6:
      if (ftype == Thrift.Type.I64) {
        this.createTimestamp = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 7:
      if (ftype == Thrift.Type.I64) {
        this.lastModifyTimestamp = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 8:
      if (ftype == Thrift.Type.STRING) {
        this.operateUser = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

BaseWorkingOrder.prototype.write = function(output) {
  output.writeStructBegin('BaseWorkingOrder');
  if (this.orderId !== null && this.orderId !== undefined) {
    output.writeFieldBegin('orderId', Thrift.Type.I64, 1);
    output.writeI64(this.orderId);
    output.writeFieldEnd();
  }
  if (this.companyId !== null && this.companyId !== undefined) {
    output.writeFieldBegin('companyId', Thrift.Type.I64, 2);
    output.writeI64(this.companyId);
    output.writeFieldEnd();
  }
  if (this.companyUserId !== null && this.companyUserId !== undefined) {
    output.writeFieldBegin('companyUserId', Thrift.Type.I64, 3);
    output.writeI64(this.companyUserId);
    output.writeFieldEnd();
  }
  if (this.workingOrderType !== null && this.workingOrderType !== undefined) {
    output.writeFieldBegin('workingOrderType', Thrift.Type.I32, 4);
    output.writeI32(this.workingOrderType);
    output.writeFieldEnd();
  }
  if (this.state !== null && this.state !== undefined) {
    output.writeFieldBegin('state', Thrift.Type.I32, 5);
    output.writeI32(this.state);
    output.writeFieldEnd();
  }
  if (this.createTimestamp !== null && this.createTimestamp !== undefined) {
    output.writeFieldBegin('createTimestamp', Thrift.Type.I64, 6);
    output.writeI64(this.createTimestamp);
    output.writeFieldEnd();
  }
  if (this.lastModifyTimestamp !== null && this.lastModifyTimestamp !== undefined) {
    output.writeFieldBegin('lastModifyTimestamp', Thrift.Type.I64, 7);
    output.writeI64(this.lastModifyTimestamp);
    output.writeFieldEnd();
  }
  if (this.operateUser !== null && this.operateUser !== undefined) {
    output.writeFieldBegin('operateUser', Thrift.Type.STRING, 8);
    output.writeString(this.operateUser);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

AssetAccountWorkingOrder = function(args) {
  this.workingOrderId = null;
  this.baseWorkingOrder = null;
  this.account = null;
  if (args) {
    if (args.workingOrderId !== undefined) {
      this.workingOrderId = args.workingOrderId;
    }
    if (args.baseWorkingOrder !== undefined) {
      this.baseWorkingOrder = args.baseWorkingOrder;
    }
    if (args.account !== undefined) {
      this.account = args.account;
    }
  }
};
AssetAccountWorkingOrder.prototype = {};
AssetAccountWorkingOrder.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I64) {
        this.workingOrderId = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRUCT) {
        this.baseWorkingOrder = new BaseWorkingOrder();
        this.baseWorkingOrder.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.STRUCT) {
        this.account = new AssetAccount();
        this.account.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

AssetAccountWorkingOrder.prototype.write = function(output) {
  output.writeStructBegin('AssetAccountWorkingOrder');
  if (this.workingOrderId !== null && this.workingOrderId !== undefined) {
    output.writeFieldBegin('workingOrderId', Thrift.Type.I64, 1);
    output.writeI64(this.workingOrderId);
    output.writeFieldEnd();
  }
  if (this.baseWorkingOrder !== null && this.baseWorkingOrder !== undefined) {
    output.writeFieldBegin('baseWorkingOrder', Thrift.Type.STRUCT, 2);
    this.baseWorkingOrder.write(output);
    output.writeFieldEnd();
  }
  if (this.account !== null && this.account !== undefined) {
    output.writeFieldBegin('account', Thrift.Type.STRUCT, 3);
    this.account.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

ReqWorkingOrderOption = function(args) {
  this.orderId = null;
  this.companyUserId = null;
  this.type = null;
  this.state = null;
  this.orderIds = null;
  this.companyId = null;
  this.companyIds = null;
  this.companyUserIds = null;
  if (args) {
    if (args.orderId !== undefined) {
      this.orderId = args.orderId;
    }
    if (args.companyUserId !== undefined) {
      this.companyUserId = args.companyUserId;
    }
    if (args.type !== undefined) {
      this.type = args.type;
    }
    if (args.state !== undefined) {
      this.state = args.state;
    }
    if (args.orderIds !== undefined) {
      this.orderIds = args.orderIds;
    }
    if (args.companyId !== undefined) {
      this.companyId = args.companyId;
    }
    if (args.companyIds !== undefined) {
      this.companyIds = args.companyIds;
    }
    if (args.companyUserIds !== undefined) {
      this.companyUserIds = args.companyUserIds;
    }
  }
};
ReqWorkingOrderOption.prototype = {};
ReqWorkingOrderOption.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I64) {
        this.orderId = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.I64) {
        this.companyUserId = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.I32) {
        this.type = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.I32) {
        this.state = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.SET) {
        var _size10 = 0;
        var _rtmp314;
        this.orderIds = [];
        var _etype13 = 0;
        _rtmp314 = input.readSetBegin();
        _etype13 = _rtmp314.etype;
        _size10 = _rtmp314.size;
        for (var _i15 = 0; _i15 < _size10; ++_i15)
        {
          var elem16 = null;
          elem16 = input.readI64().value;
          this.orderIds.push(elem16);
        }
        input.readSetEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 6:
      if (ftype == Thrift.Type.I64) {
        this.companyId = input.readI64().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 7:
      if (ftype == Thrift.Type.SET) {
        var _size17 = 0;
        var _rtmp321;
        this.companyIds = [];
        var _etype20 = 0;
        _rtmp321 = input.readSetBegin();
        _etype20 = _rtmp321.etype;
        _size17 = _rtmp321.size;
        for (var _i22 = 0; _i22 < _size17; ++_i22)
        {
          var elem23 = null;
          elem23 = input.readI64().value;
          this.companyIds.push(elem23);
        }
        input.readSetEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 8:
      if (ftype == Thrift.Type.SET) {
        var _size24 = 0;
        var _rtmp328;
        this.companyUserIds = [];
        var _etype27 = 0;
        _rtmp328 = input.readSetBegin();
        _etype27 = _rtmp328.etype;
        _size24 = _rtmp328.size;
        for (var _i29 = 0; _i29 < _size24; ++_i29)
        {
          var elem30 = null;
          elem30 = input.readI64().value;
          this.companyUserIds.push(elem30);
        }
        input.readSetEnd();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

ReqWorkingOrderOption.prototype.write = function(output) {
  output.writeStructBegin('ReqWorkingOrderOption');
  if (this.orderId !== null && this.orderId !== undefined) {
    output.writeFieldBegin('orderId', Thrift.Type.I64, 1);
    output.writeI64(this.orderId);
    output.writeFieldEnd();
  }
  if (this.companyUserId !== null && this.companyUserId !== undefined) {
    output.writeFieldBegin('companyUserId', Thrift.Type.I64, 2);
    output.writeI64(this.companyUserId);
    output.writeFieldEnd();
  }
  if (this.type !== null && this.type !== undefined) {
    output.writeFieldBegin('type', Thrift.Type.I32, 3);
    output.writeI32(this.type);
    output.writeFieldEnd();
  }
  if (this.state !== null && this.state !== undefined) {
    output.writeFieldBegin('state', Thrift.Type.I32, 4);
    output.writeI32(this.state);
    output.writeFieldEnd();
  }
  if (this.orderIds !== null && this.orderIds !== undefined) {
    output.writeFieldBegin('orderIds', Thrift.Type.SET, 5);
    output.writeSetBegin(Thrift.Type.I64, this.orderIds.length);
    for (var iter31 in this.orderIds)
    {
      if (this.orderIds.hasOwnProperty(iter31))
      {
        iter31 = this.orderIds[iter31];
        output.writeI64(iter31);
      }
    }
    output.writeSetEnd();
    output.writeFieldEnd();
  }
  if (this.companyId !== null && this.companyId !== undefined) {
    output.writeFieldBegin('companyId', Thrift.Type.I64, 6);
    output.writeI64(this.companyId);
    output.writeFieldEnd();
  }
  if (this.companyIds !== null && this.companyIds !== undefined) {
    output.writeFieldBegin('companyIds', Thrift.Type.SET, 7);
    output.writeSetBegin(Thrift.Type.I64, this.companyIds.length);
    for (var iter32 in this.companyIds)
    {
      if (this.companyIds.hasOwnProperty(iter32))
      {
        iter32 = this.companyIds[iter32];
        output.writeI64(iter32);
      }
    }
    output.writeSetEnd();
    output.writeFieldEnd();
  }
  if (this.companyUserIds !== null && this.companyUserIds !== undefined) {
    output.writeFieldBegin('companyUserIds', Thrift.Type.SET, 8);
    output.writeSetBegin(Thrift.Type.I64, this.companyUserIds.length);
    for (var iter33 in this.companyUserIds)
    {
      if (this.companyUserIds.hasOwnProperty(iter33))
      {
        iter33 = this.companyUserIds[iter33];
        output.writeI64(iter33);
      }
    }
    output.writeSetEnd();
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

AssetAccountWorkingOrderPage = function(args) {
  this.total = null;
  this.page = null;
  if (args) {
    if (args.total !== undefined) {
      this.total = args.total;
    }
    if (args.page !== undefined) {
      this.page = args.page;
    }
  }
};
AssetAccountWorkingOrderPage.prototype = {};
AssetAccountWorkingOrderPage.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I32) {
        this.total = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.LIST) {
        var _size34 = 0;
        var _rtmp338;
        this.page = [];
        var _etype37 = 0;
        _rtmp338 = input.readListBegin();
        _etype37 = _rtmp338.etype;
        _size34 = _rtmp338.size;
        for (var _i39 = 0; _i39 < _size34; ++_i39)
        {
          var elem40 = null;
          elem40 = new AssetAccountWorkingOrder();
          elem40.read(input);
          this.page.push(elem40);
        }
        input.readListEnd();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

AssetAccountWorkingOrderPage.prototype.write = function(output) {
  output.writeStructBegin('AssetAccountWorkingOrderPage');
  if (this.total !== null && this.total !== undefined) {
    output.writeFieldBegin('total', Thrift.Type.I32, 1);
    output.writeI32(this.total);
    output.writeFieldEnd();
  }
  if (this.page !== null && this.page !== undefined) {
    output.writeFieldBegin('page', Thrift.Type.LIST, 2);
    output.writeListBegin(Thrift.Type.STRUCT, this.page.length);
    for (var iter41 in this.page)
    {
      if (this.page.hasOwnProperty(iter41))
      {
        iter41 = this.page[iter41];
        iter41.write(output);
      }
    }
    output.writeListEnd();
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

