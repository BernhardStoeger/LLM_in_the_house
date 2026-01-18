import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map, Observable} from 'rxjs';

export interface LLMMessage {
  model: string,
  message: [{role: string, content: string}];
}

export interface LLMModel {
  name: string
}

export interface ChatMessage {
  role: 'user' | 'llm';
  content: string;
  timestamp: Date;
}

export interface Response {
  message: {content: string};
}

const port = '/api/chat'

@Injectable({ providedIn: 'root' })
export class LLMChatService {
  // Example LLMs, replace with dynamic fetch if needed
  private llms: LLMModel[] = [
    { name: 'editus'},
    { name: 'Dnd helper'}
  ];

  constructor(private http: HttpClient) {}

  getAvailableLLMs(): LLMModel[] {
    return this.llms;
  }

  sendMessage(llm: LLMModel, msg: string): Observable<string> {
    const message: LLMMessage = {
      model: llm.name,
      message: [{role: 'user', content: msg}]
    };
    return this.http.post<any>(port, message ).pipe(
      map(res => {
        return res.message.content;
      })
    );
  }
}
