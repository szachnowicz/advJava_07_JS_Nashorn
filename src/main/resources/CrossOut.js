function CrossOut(html){

    var str = html.getText();
 	var some = str.replace("<h2>", "<h2><Strike>");
 	var x = some.replace("</h2>", "</Strike></h2>");
    html.setText(x);

}
