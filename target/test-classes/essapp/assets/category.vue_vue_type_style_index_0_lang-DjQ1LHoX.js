import{ag as x,a3 as w,H as C,w as B,bf as L,X as F,a9 as N}from"./antd-CGu55NZb.js";import{d as V,s as _,f as $,a2 as r,a9 as p,aa as t,k as a,a4 as d,a3 as S,aj as T,u as c,F as j,G as D,ad as E}from"./vue-1GTWcegU.js";const G={class:"flex flex-wrap gap-2"},H={class:"flex gap-4 category-other-item"},I=V({__name:"category",setup(R){const u=_([{name:"全部",key:"all"},...["一","二","三","四","五","六","七","八","九","十","十一","十二"].map((n,l)=>({name:`类目${n}`,key:`category${l+1}`}))]),e=$([]);function f(n){if(n.key==="all"){if(e.value.includes("all")){e.value=[];return}else e.value=u.value.map(l=>l.key);return}e.value.includes(n.key)?(e.value=e.value.filter(l=>l!==n.key),e.value.includes("all")&&(e.value=e.value.filter(l=>l!=="all"))):(e.value=[...e.value,n.key],e.value.length===u.value.length-1&&(e.value=[...e.value,"all"]))}const m=_([{label:"付晓晓",value:"付晓晓"},{label:"周毛毛",value:"周毛毛"}]),v=_([{label:"优秀",value:1},{label:"普通",value:2}]);return(n,l)=>{const k=N,o=w,y=C,i=B,g=L,h=F,b=x;return r(),p(b,{bordered:!1},{default:t(()=>[a(h,null,{default:t(()=>[a(o,{label:"所属类目"},{default:t(()=>[d("div",G,[(r(!0),S(j,null,T(c(u),s=>(r(),p(k,{key:s.key,"cursor-pointer":"",color:c(e).includes(s.key)?"#108ee9":"",onClick:q=>f(s)},{default:t(()=>[D(E(s.name),1)]),_:2},1032,["color","onClick"]))),128))])]),_:1}),a(y,{dashed:""}),a(o,{label:"其他选项"},{default:t(()=>[a(g,null,{default:t(()=>[d("div",H,[a(o,{label:"作者"},{default:t(()=>[a(i,{placeholder:"不限",style:{width:"100px"},options:c(m)},null,8,["options"])]),_:1}),a(o,{label:"好评度"},{default:t(()=>[a(i,{placeholder:"不限",style:{width:"100px"},options:c(v)},null,8,["options"])]),_:1})])]),_:1})]),_:1})]),_:1})]),_:1})}}});export{I as _};
