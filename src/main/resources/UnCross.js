function UnCross(html){

    var str = html.getText();
 	var some = str.replace("<h2><Strike>", "<h2>");
 	var x = some.replace("</Strike></h2>", "</h2>");
    html.setText(x);


}
