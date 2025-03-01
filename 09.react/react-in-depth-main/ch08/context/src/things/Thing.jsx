import styled from "styled-components";

import { useThing } from "../data";
import { Button } from "./Button";
import { Progress } from "./Progress";
import { ThingTitle } from "./ThingTitle";

const Section = styled.section`
  border: 2px solid black;
  border-radius: 0.5em;
  padding: 2em;
  display: flex;
  flex-direction: column;
  align-items: stretch;
  gap: 2em;
  width: clamp(10em, 90vw, 60em);
  box-shadow: -6px 6px 0 black;
`;

const Body = styled.div`
  display: flex;
  align-items: center;
  gap: 1em;
`;

export function Thing({ id }) {
  const { thing, seeThing, doThing, undoLastThing } = useThing(id);
  return (
    <Section>
      <ThingTitle
        name={thing.name}
        count={thing.done.length}
        onDetails={seeThing}
      />
      <Body>
        <Progress value={thing.done.length} />
        <Button onClick={doThing}>➕</Button>
        <Button onClick={undoLastThing}>➖</Button>
      </Body>
    </Section>
  );
}
