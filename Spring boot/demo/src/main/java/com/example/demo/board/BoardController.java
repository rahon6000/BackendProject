package com.example.demo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardRepository boardRepository;
  @Autowired
  private ReplyRepository replyRepository;
  
  // 나중에 자잘한 부분들은 서비스로 리팩터링 합시다.
  // 이렇게 기능별로 따로 떨어뜨려 놓는게 좋음. 이 주석 적을 때도 CRUD -> Page 로 인터페이스 바꾸는데
  // 여기만 바꾸면 되니까.
  private ArrayList<BoardVO> getBoards(int pageNum, int listingNum, Model model){
    ArrayList<BoardVO> boardList = new ArrayList<>();    
    PageRequest page = PageRequest.of(pageNum, listingNum, Sort.by("id").descending());
    for(BoardVO b : boardRepository.findAll(page).getContent()){
      boardList.add(b);
    }
    int pagination = (int)boardRepository.count()/10;
    model.addAttribute("pagination", pagination + 1);
    return boardList;
  }

  @GetMapping("/")
  public String boardList(Model model) {
    model.addAttribute("boardList", getBoards(0, 10, model));
    model.addAttribute("pageNum", 1);
    int pagination = (int)boardRepository.count()/10;
    model.addAttribute("pagination", pagination + 1);
    return "board";
  }

  @GetMapping("/{pageNum}")
  public String boardList(Model model, 
      @PathVariable(name = "pageNum") Integer pageNum) {
    model.addAttribute("boardList", getBoards(pageNum-1, 10, model));
    model.addAttribute("pageNum", pageNum-1);
    return "board";
  }
  
  @GetMapping("/{pageNum}/{boardId}")
  public String boardList(Model model, 
      @PathVariable(name = "boardId") Integer boardId,
      @PathVariable(name = "pageNum") Integer pageNum) {
    BoardVO board = boardRepository.findById(boardId).get();
    model.addAttribute("showBoard", board);
    model.addAttribute("boardList", getBoards(pageNum-1, 10, model));
    model.addAttribute("pageNum", pageNum);
    model.addAttribute("replyForm", new ReplyVO());
    // add comments
    ArrayList<ReplyVO> replyList = replyRepository.findAllByBoardOrderByIdAsc(board);
    model.addAttribute("replyList", replyList);
    for (ReplyVO r : replyList) {
      System.out.println(r);
    }
    return "board";
  }

  @PostMapping(value="/add")
  public String addBoard(@RequestParam String name, @RequestParam String title,
  @RequestParam String content, @RequestParam MultipartFile[] files ) {
    for(MultipartFile f : files) {
      System.out.println("-----------");
      System.out.println("file name : " + f.getOriginalFilename());
      System.out.println("file size : " + f.getSize());
    }
    BoardVO board = new BoardVO();
    board.setAuthor(name);
    board.setContent(content); 
    board.setTitle(title);
    board.setWriteDate(new Date());
    board.setFile(!files[0].isEmpty());
    boardRepository.save(board);
    return "redirect:/board/";
  }

  /**####################################################################################
   * 코멘트 세이브 (원래는 서비스에 있어야지...)
   * @param replyName 
   * @param replyContent
   * @param boardId
   * @param boardPage
   * @return
   * ####################################################################################
   */  
  @PostMapping(value="/reply")
  public String postMethodName(
      @RequestParam String replyName, 
      @RequestParam String replyContent,
      @RequestParam int boardId,
      @RequestParam int boardPage) {
    ReplyVO reply = new ReplyVO();
    BoardVO board = boardRepository.findById(boardId).get();
    reply.setAuthor(replyName);
    reply.setContent(replyContent);
    reply.setBoard(board);
    replyRepository.save(reply);

    return "redirect:/board/"+ boardPage + "/" + boardId;
  }
  
  
}
