function UnboldText(html){

    var str = html.getText();
 	var some = str.replace(/<p><B>/g, "<p>");
 	var x = some.replace(/<\/p><\/B>/g, "</p>");
    html.setText(x);
}
