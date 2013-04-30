<?php
include 'NestedComment.php';
$comments = array(  array('comment_id'=>1, 'parent_id'=>0,   'content'=>'Parent', 'writer_name'=> 'huangfu'),
array('comment_id'=>2, 'parent_id'=>1,      'content'=>'Child', 'writer_name'=> 'huangfu'),
array('comment_id'=>3, 'parent_id'=>2,      'content'=>'Child Third level', 'writer_name'=> 'huangfu'),
array('comment_id'=>4, 'parent_id'=>0,   'content'=>'Second Parent' ,'writer_name'=> 'huangfu'),
array('comment_id'=>5, 'parent_id'=>4,   'content'=>'Second Child', 'writer_name'=> 'huangfu')
);

$threaded_comments = new Threaded_comments($comments);

$threaded_comments->print_comments();