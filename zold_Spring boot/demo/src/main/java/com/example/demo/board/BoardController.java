package com.example.demo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/board")
@Log4j2
public class BoardController {

  @Autowired
  private BoardRepository boardRepository;
  @Autowired
  private ReplyRepository replyRepository;

  // CRUD repo -> Page repo
  private ArrayList<BoardVO> getBoards(int pageNum, int listingNum, Model model) {
    ArrayList<BoardVO> boardList = new ArrayList<>();
    PageRequest page = PageRequest.of(pageNum, listingNum, Sort.by("id").descending());
    for (BoardVO b : boardRepository.findAll(page).getContent()) {
      boardList.add(b);
    }
    int pagination = (int) boardRepository.count() / 10;
    model.addAttribute("pagination", pagination + 1);
    model.addAttribute("board", new BoardVO());
    return boardList;
  }

  @GetMapping("/")
  public String boardList(Model model) {
    model.addAttribute("boardList", getBoards(0, 10, model));
    model.addAttribute("pageNum", 1);
    int pagination = (int) boardRepository.count() / 10;
    model.addAttribute("pagination", pagination + 1);
    return "board";
  }

  @GetMapping("/{pageNum}")
  public String boardList(Model model,
      @PathVariable(name = "pageNum") Integer pageNum) {
    model.addAttribute("boardList", getBoards(pageNum - 1, 10, model));
    model.addAttribute("pageNum", pageNum);
    return "board";
  }

  @GetMapping("/{pageNum}/{boardId}")
  public String getBoard(Model model,
      @PathVariable(name = "boardId") Integer boardId,
      @PathVariable(name = "pageNum") Integer pageNum) {
    BoardVO board = boardRepository.findById(boardId).get();
    model.addAttribute("showBoard", board);
    model.addAttribute("boardList", getBoards(pageNum - 1, 10, model));
    model.addAttribute("pageNum", pageNum);
    model.addAttribute("replyForm", new ReplyVO());
    // add comments
    ArrayList<ReplyVO> replyList = replyRepository.findAllByBoardOrderByIdAsc(board);
    model.addAttribute("replyList", replyList);
    model.addAttribute("reply", new ReplyVO());
    for (ReplyVO r : replyList) {
      System.out.println(r);
    }
    return "board";
  }

  @PostMapping(value = "/{pageNum}/{boardId}/modify")
  public String modifyBoard(
      @PathVariable int boardId,
      BoardVO board,
      HttpServletResponse response) {
    BoardVO target = boardRepository.findById(boardId).get();
    log.info(board);
    if (target.getPassword().equals(board.getPassword())) { // password match
      target.setAuthor(board.getAuthor());
      target.setContent(board.getContent());
      target.setTitle(board.getTitle());
      target.setWriteDate(new Date());
      boardRepository.save(target);
      return "redirect:/board/";
    } else {// wrong password
      // Servlet response 객체를 직접 사용 (parameter 로 받아와야 함)
      // 거의 비어있는 단순한 JS 페이지 리턴할 땐 쓸만할 듯.
      response.setContentType("text/html; charset=UTF-8");
      try {
        response.getWriter().println("<script>alert('wrong password.'); history.back(-1);</script>");
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null; // show nothing!
    }
  }

  @PostMapping(value = "/{pageNum}/{boardId}/delete")
  public String deleteBoard(
      @PathVariable int pageNum,
      @PathVariable int boardId,
      @RequestParam(name = "password") String password,
      HttpServletResponse response) {
    BoardVO target = boardRepository.findById(boardId).get();
    if (target.getPassword().equals(password)) { // password match
      log.info("delte" + boardId);
      boardRepository.deleteById(boardId);
      return "redirect:/board/" + pageNum;
    } else {// wrong password
      // Servlet response 객체를 직접 사용 (parameter 로 받아와야 함)
      // 거의 비어있는 단순한 JS 페이지 리턴할 땐 쓸만할 듯.
      response.setContentType("text/html; charset=UTF-8");
      try {
        response.getWriter().println("<script>alert('wrong password.'); history.back(-1);</script>");
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null; // show nothing!
    }
  }

  @PostMapping(value = "/add")
  public String addBoard(
      BoardVO board,
      @RequestParam("files") MultipartFile[] files) {
    log.info(board);
    for (MultipartFile f : files) {
      if(f.getSize() == 0) break;
      System.out.println("-----------");
      System.out.println("file name : " + f.getOriginalFilename());
      System.out.println("file size : " + f.getSize());
      try{
        f.getBytes();
      } catch (IOException e){
        e.printStackTrace();
      }
      board.setFile(true);
    }
    // BoardVO board = new BoardVO();
    // board.setFile(!files[0].isEmpty());
    board.setWriteDate(new Date());
    boardRepository.save(board);
    return "redirect:/board/";
  }

  /**
   * ####################################################################################
   * 코멘트 세이브 (원래는 서비스에 있어야지...)
   * 
   * @param replyName
   * @param replyContent
   * @param boardId
   * @param boardPage
   * @return
   *         ####################################################################################
   */
  @PostMapping(value = "/reply")
  public String postMethodName(
      ReplyVO reply,
      @RequestParam int boardId,
      @RequestParam int boardPage) {
    BoardVO board = boardRepository.findById(boardId).get();
    reply.setBoard(board);
    replyRepository.save(reply);
    return "redirect:/board/" + boardPage + "/" + boardId;
  }

}
