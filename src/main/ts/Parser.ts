
import { parseAndGenerateServices } from '@typescript-eslint/typescript-estree';
// import * as fs from 'fs';
// import * as ts from "typescript";

export class Parser {

    parse(code: string, sourceFilePath: string) {
        const { ast, services } = parseAndGenerateServices(code, {
        filePath: sourceFilePath,
        loc: true,
        //project: projectFilePath,
        tsconfigRootDir: module.path,
        range: true,
        });

        return ast;

    }

    hello(message: string) {
        return "HELLO " + message;
    }
}
