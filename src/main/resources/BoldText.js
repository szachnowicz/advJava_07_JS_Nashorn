function BoldText(html){

    var str = html.getText();
 	var some = str.replace(/<p>/g, "<p><B>");
 	var x = some.replace(/<\/p>/g, "</B></p>");
    html.setText(x);


}
