let days=["Sunday   ","   Monday  ",

"  Tuesday","Wednesday  ","  Thursday   ","   Friday",

"Saturday    "]
toUpper = function(x){ 
    return x.trim().toUpperCase().substring(0,3);
  };
console.log(days)
days = days.map(toUpper);
console.log(days)


function modifystr(x){
    x=x.trimStart().trimEnd().replace('a',4).replace('e',3).replace('i',1).replace('o',0).replace('s',5)
    return x
}
let sample1 = "javascript is cool"
let sample2 = "programming is fun"
let sample3 = "  become a coder"
console.log(modifystr(sample1))
console.log(modifystr(sample2))
console.log(modifystr(sample3))
