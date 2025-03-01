import styled from "@emotion/styled";
import { Status, WinningGridWord } from "../../types";
import { Cell } from "./Cell";
import { RowElement } from "./Row";
import { useEffectMachine } from "./useEffectMachine";

const JumpingRow = styled(Cell)<{ $isJumping: boolean }>`
  translate: 0 ${({ $isJumping }) => ($isJumping ? -30 : 0)}%;
  transition: translate 0.1s linear;
  box-shadow: 0 0 3px rgba(0 0 0 / 0.5);
`;

interface WinRowProps extends Pick<WinningGridWord, "word"> {
  onEffect?: () => void;
}

export function WinRow({ word, onEffect }: WinRowProps) {
  const characters = word.split("");
  const { active, next } = useEffectMachine(onEffect);
  return (
    <RowElement>
      {characters.map((key, index) => (
        <JumpingRow
          key={index}
          char={key}
          $isJumping={active === index}
          onTransitionEnd={
            active === index || index === 4 ? next : undefined
          }
          status={Status.Correct}
        />
      ))}
    </RowElement>
  );
}
